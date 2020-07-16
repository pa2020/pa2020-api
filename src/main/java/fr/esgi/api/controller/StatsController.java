package fr.esgi.api.controller;


import fr.esgi.api.models.statistics.Stats;
import fr.esgi.api.services.statistics.IStatsService;
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
@RequestMapping("/api/v1/stats")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class StatsController {

    private final IStatsService statsService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Stats> getStatsOfWord(@RequestParam(required = false) String word) {
        return statsService.listStatsOfWord(word);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Stats> createStats(@RequestBody Stats stats) {
        logger.info("< log bodyStats:{}", stats.getId());
        Optional<Stats> createStats = Optional.ofNullable(statsService.create(stats));
        if (createStats.isEmpty()) {
            return new ResponseEntity<Stats>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Stats>(stats, HttpStatus.OK);
    }
}
