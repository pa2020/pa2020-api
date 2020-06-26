package fr.esgi.api.controller;

import fr.esgi.api.exception.ResourceNotFoundException;
import fr.esgi.api.models.request.Request;
import fr.esgi.api.services.IRequestService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


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
    private final RestTemplate restTemplate;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Request>> getAll() {
        logger.info("> requests");
        List<Request> requestList = requestService.findAll();
        return new ResponseEntity<List<Request>>(requestList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Request> getRequestById(@PathVariable("id") Long id) {
        logger.info("> getRequest id:{}", id);
        Optional<Request> request = requestService.findById(id);
        if (request.isEmpty()) {
            throw new ResourceNotFoundException("Request "+ id +" not found!");
        }
        logger.info("< getRequest id:{}", id);
        return new ResponseEntity<Request>(request.get(), HttpStatus.OK);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Request> getRequestByUser_Id(@RequestParam(required=false) Long id){
        return requestService.findRequestByUser_Id(id);
    }

    @PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String sendRequest(@RequestBody Request request) {
        //Request savedRequest= requestService.create(request);
        logger.info("< sendRequest bodyRequest:{}", request.getSentence());
        String url = "http://wiirlak.dynamic-dns.net:2000/analyze";
        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        // create a post object'[
        Request savedRequest = requestService.create(request);
        if (savedRequest.getSentence() == null){
            throw new ResourceNotFoundException("Your sentence is empty");
        }else {
            // build the request
            HttpEntity<Request> entity = new HttpEntity<>(savedRequest, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            // check response status code
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new ResourceNotFoundException("Request i'not send to client lourd!");
            }
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Request> updateRequest(@RequestBody Request request, @PathVariable Long id) {
        logger.info("> updateRequest id:{}", request.getRequest_id());
        Request updateRequest = requestService.update(request, id);
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
}
