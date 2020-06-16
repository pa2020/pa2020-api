package fr.esgi.api.controller;


import fr.esgi.api.models.request.AnalyzeRequest;
import fr.esgi.api.services.IAnalyzeRequestService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/analyzes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AnalyzeRequestController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IAnalyzeRequestService analyzeRequestService;


    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<AnalyzeRequest> createAnalyzeRequest(@RequestBody AnalyzeRequest analyzeRequest) {
        logger.info("< log bodyAnalyzeRequest:{}", analyzeRequest.getAnalyze_r_id());
        Optional<AnalyzeRequest> createAnalyzeRequest = Optional.ofNullable(analyzeRequestService.create(analyzeRequest));
        if (createAnalyzeRequest.isEmpty()) {
            return new ResponseEntity<AnalyzeRequest>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< updateRequest id:{}", analyzeRequest.getAnalyze_r_id());
        return new ResponseEntity<AnalyzeRequest>(analyzeRequest, HttpStatus.OK);
    }

}
