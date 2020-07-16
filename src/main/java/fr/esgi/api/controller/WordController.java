package fr.esgi.api.controller;

import fr.esgi.api.exception.ResourceNotFoundException;
import fr.esgi.api.models.statistics.Word;
import fr.esgi.api.services.statistics.IWordService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zakaria FAHRAOUI.
 */

@RestController
@RequestMapping("/api/v1/word")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class WordController {

    private final IWordService wordService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Word>> getAll() {
        logger.info("> Words");
        List<Word> wordList = wordService.findAll();
        return new ResponseEntity<List<Word>>(wordList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Word> getWordById(@PathVariable("id") Long id) {
        logger.info("> getWord id:{}", id);
        Optional<Word> word = wordService.findById(id);
        if (word.isEmpty()) {
            throw new ResourceNotFoundException("Word " + id + " not found!");
        }
        logger.info("< getWord id:{}", id);
        return new ResponseEntity<Word>(word.get(), HttpStatus.OK);
    }


    @PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public Word sendWord(@RequestBody Word word) {
        logger.info("< sendRequest bodyWord:{}", word.getWord());
        Word savedWord = wordService.create(word);
        if (word.getWord().isEmpty() || word.getWord() == null) {
            throw new ResourceNotFoundException("Your word is empty");
        } else {
            logger.info("Your word is exists");
            return savedWord;
        }
    }
}
