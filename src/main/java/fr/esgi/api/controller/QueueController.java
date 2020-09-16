package fr.esgi.api.controller;

import fr.esgi.api.models.queue.Queue;
import fr.esgi.api.services.queue.IQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Alexis DESJARDINS.
 */

@RestController
@RequestMapping("/api/v1/queue")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class QueueController {
    private final IQueueService queueService;

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Queue> createQueue(@RequestBody @Valid Queue queue) {
        return new ResponseEntity<>(queueService.createQueue(queue), HttpStatus.OK);
    }

    @GetMapping("/user_id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> getId(@RequestParam(required = false) Long id) {
        return queueService.getQueueByRequestId(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> deleteRequest(@PathVariable("id") Long id) {
        queueService.deleteQueueByRequestId(id);
        return ResponseEntity.ok().body("Your request has been deleted");
    }
}
