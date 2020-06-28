package fr.esgi.api.controller;

import fr.esgi.api.exception.ResourceNotFoundException;
import fr.esgi.api.models.statistics.Word;
import fr.esgi.api.services.statistics.IWordService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by Zakaria FAHRAOUI.
 */

@RestController
@RequestMapping("/api/v1/word")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class WordController {

    private final IWordService wordService;
    private final RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public String sendWord(@RequestBody Word word) {
        logger.info("< sendRequest bodyWord:{}", word.getWord());
        String url = "http://wiirlak.dynamic-dns.net:2000/analyze";
        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        // create a post object'[
        Word savedWord = wordService.create(word);
        if (word.getWord().isEmpty() || word.getWord() == null) {
            throw new ResourceNotFoundException("Your word is empty");
        } else {
            // build the request
            HttpEntity<Word> entity = new HttpEntity<>(savedWord, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            // check response status code
            if (response.getStatusCode() == HttpStatus.OK) {
                logger.info("Your word is exists");
                return response.getBody();
            } else {
                throw new ResourceNotFoundException("Word i'not send to client lourd!");
            }
        }
    }
}
