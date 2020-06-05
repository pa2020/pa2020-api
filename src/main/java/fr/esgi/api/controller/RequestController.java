package fr.esgi.api.controller;


import fr.esgi.api.exception.ResourceNotFoundException;
import fr.esgi.api.models.request.Request;
import fr.esgi.api.services.IRequestService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

/**
 * Created by Zakaria FAHRAOUI.
 */

@RestController
@RequestMapping("/api/v1/requests")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RequestController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IRequestService requestService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Request>> getAll(){
        logger.info("> requests");
        List<Request> requestList = requestService.findAll();
        return new ResponseEntity<List<Request>>(requestList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Request> getGreeting(@PathVariable("id") Long id) {
        logger.info("> getRequest id:{}", id);
        Request request = requestService.findById(id).get();
        if (request == null) {
            return new ResponseEntity<Request>(HttpStatus.NOT_FOUND);
        }
        logger.info("< getRequest id:{}", id);
        return new ResponseEntity<Request>(request, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        logger.info("> createRequest");

        Request savedRequest= requestService.create(request);

        logger.info("< createRequest");
        return new ResponseEntity<Request>(savedRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Request> updateRequest(@RequestBody Request request) {
        logger.info("> updateRequest id:{}", request.getRequest_id());

        Request updateRequest = requestService.update(request);
        if (updateRequest == null) {
            return new ResponseEntity<Request>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("< updateRequest id:{}", request.getRequest_id());
        return new ResponseEntity<Request>(updateRequest, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Request> deleteRequest(@PathVariable("id") Long id, @RequestBody Request request) {
        logger.info("> deleteRequest id:{}", id);

        requestService.delete(id);

        logger.info("< deleteRequest id:{}", id);
        return new ResponseEntity<Request>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/send")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Request> sendRequest(@PathVariable("id") Long id, @RequestParam(value = "wait", defaultValue = "false") boolean waitForAsyncResult) {

        logger.info("> sendRequest id:{}", id);

        Request request = null;

        try {
            request = requestService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found for this id : " + id));
            if (request == null) {
                logger.info("< sendRequest id:{}", id);
                return new ResponseEntity<Request>(HttpStatus.NOT_FOUND);
            }

            if (waitForAsyncResult) {
                Future<Boolean> asyncResponse = requestService
                        .sendAsyncWithResult(request);
                boolean RequestSent = asyncResponse.get();
                logger.info("- request client lourd sent? {}", RequestSent);
            } else {
                requestService.sendAsync(request);
            }
        } catch (Exception e) {
            logger.error("A problem occurred sending the Request.", e);
            return new ResponseEntity<Request>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("< sendRequest id:{}", id);
        return new ResponseEntity<Request>(request, HttpStatus.OK);
    }

}
