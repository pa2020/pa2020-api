package fr.esgi.api.dto.queue;

import com.google.gson.Gson;
import fr.esgi.api.exception.ResourceNotFoundException;
import fr.esgi.api.models.queue.Queue;
import fr.esgi.api.repositories.queue.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Component
@RequiredArgsConstructor
public class QueueDto implements IQueueDto {

    private final QueueRepository queueRepository;

    @Override
    public Queue createQueue(Queue queue) {
        return queueRepository.save(queue);
    }

    /**
     * Search an existing Queue
     *
     * @param user_id
     * @return Queue object
     */
    @Override
    public ResponseEntity<String> getQueueByRequestId(Long user_id) {
        Gson gson = new Gson();
        List<Queue> all_queue = queueRepository.findAll();
        List<Queue> queueListByUser = queueRepository.listRequestByUserId(user_id);
        if (all_queue.isEmpty()) {
            return new ResponseEntity<>(all_queue.toString(), HttpStatus.OK);
        }
        List<ExtendedQueue> LQR = new ArrayList<>();
        for (int i = 0; i < all_queue.size(); i++) {
            for (int j = 0; j < queueListByUser.size(); j++) {
                if (all_queue.get(i) == queueListByUser.get(j)) {
                    ExtendedQueue equeue = new ExtendedQueue(queueListByUser.get(j), i, all_queue.size());
                    LQR.add(equeue);
                }
            }
        }
        if (LQR.size() == 0) {
            return new ResponseEntity<>(LQR.toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>(gson.toJson(LQR), HttpStatus.OK);
    }

    /**
     * Delete an existing Queue
     *
     * @param request_id
     */
    @Override
    public void deleteQueueByRequestId(Long request_id) {
        if (request_id <= 0) {
            throw new ResourceNotFoundException("The given id must not be null!");
        } else {
            queueRepository.deleteRequestByRequestId(request_id);
        }
    }

    private static class ExtendedQueue {
        Queue queue;
        int position;
        int total;

        public ExtendedQueue(Queue queue, int position, int total) {
            this.queue = queue;
            this.position = position;
            this.total = total;
        }
    }
}
