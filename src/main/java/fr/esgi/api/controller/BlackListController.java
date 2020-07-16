package fr.esgi.api.controller;

import fr.esgi.api.models.request.BlackList;
import fr.esgi.api.services.request.IBlackListService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Zakaria FAHRAOUI.
 */

@RestController
@RequestMapping("/api/v1/blacklist")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BlackListController {

    private final IBlackListService blackListService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BlackList>> getAll() {
        logger.info("> BlackList");
        List<BlackList> blackList = blackListService.AllBlackList();
        return new ResponseEntity<List<BlackList>>(blackList, HttpStatus.OK);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BlackList>> getBySentence(@RequestParam(required = false) String word) {

        if (word.isEmpty() || word == null) {
            return new ResponseEntity<List<BlackList>>(HttpStatus.NO_CONTENT);
        } else {
            try {
                List<BlackList> blackList = blackListService.SearchBySentence(word);
                if (blackList.isEmpty() || blackList == null) {
                    return new ResponseEntity<List<BlackList>>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<List<BlackList>>(blackList, HttpStatus.OK);
                }
            } catch (Exception e) {
                return new ResponseEntity<List<BlackList>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping(value = "/send")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BlackList> sendBlackList(@RequestBody BlackList blackList) {

        if (blackList.getSentence().isEmpty() || blackList.getSentence() == null) {
            return new ResponseEntity<BlackList>(HttpStatus.NO_CONTENT);
        } else {
            try {
                BlackList saveBlackList = blackListService.addBlackListSentence(blackList);
                return new ResponseEntity<BlackList>(saveBlackList, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<BlackList>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteRequest(@PathVariable("id") Long id) {
        blackListService.deleteBlackList(id);
        return ResponseEntity.ok().body("Your Sentence in black list has been deleted");
    }
}
