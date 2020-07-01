package fr.esgi.api.controller;

import fr.esgi.api.exception.ResourceNotFoundException;
import fr.esgi.api.models.request.BlackList;
import fr.esgi.api.models.statistics.Word;
import fr.esgi.api.services.request.IBlackListService;
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
@RequestMapping("/api/v1/blacklist")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BlackListController {

    private final IBlackListService blackListService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BlackList>> getAll() {
        logger.info("> BlackList");
        List<BlackList> blackList = blackListService.AllBlackList();
        return new ResponseEntity<List<BlackList>>(blackList, HttpStatus.OK);
    }

    @PostMapping(value = "/send")
    @PreAuthorize("hasRole('ADMIN')")
    public BlackList sendBlackList(@RequestBody BlackList blackList) {

        if (blackList.getSentence().isEmpty() || blackList.getSentence() == null) {
            throw new ResourceNotFoundException("Your Sentence is empty");
        } else {
            return blackListService.addBlackListSentence(blackList);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteRequest(@PathVariable("id") Long id) {
        blackListService.deleteBlackList(id);
        return ResponseEntity.ok().body("Your Sentence in black list has been deleted");
    }
}
