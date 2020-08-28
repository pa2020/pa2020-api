package fr.esgi.api.dto.queue;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.esgi.api.exception.ResourceNotFoundException;
import fr.esgi.api.models.queue.Queue;
import fr.esgi.api.models.request.Request;
import fr.esgi.api.models.user.User;
import fr.esgi.api.repositories.queue.QueueRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexis DESJARDINS.
 */

@Component
@RequiredArgsConstructor
public class QueueDto implements IQueueDto {

    private final QueueRepository QueueRepository;

    @Override
    public Queue createQueue(Queue queue) {
        Gson gson = new Gson();
        return QueueRepository.save(queue);
    }

    /**
     * Search an existing Queue
     *
     * @param request_id
     * @return Queue object
     */
    @Override
    public String getQueueByRequestId(Long request_id) {
        List<Queue> all_queue = QueueRepository.findAll();
        long min_id;
        if (!all_queue.isEmpty()) {
            min_id = all_queue.stream()
                    .mapToLong(Queue::getRequestId)
                    .min()
                    .orElse(0);
        } else
            throw new RuntimeException("File d'attente vide");

        Optional<Queue> Queue = Optional.of(all_queue.stream()
                .filter(queue -> queue.getRequestId().equals(request_id)))
                .get()
                .findAny();
        if (Queue.isPresent()) {
            Queue queue = Queue.get();
            int position = (int) (queue.getId() - min_id);
            int total = all_queue.size();
            ExtendedQueue equeue = new ExtendedQueue(queue, position, total);
            return new Gson().toJson(equeue);
        } else
            throw new RuntimeException("Requête non présente dans la file d'attente");
    }

    /**
     * Delete an existing Queue
     *
     * @param request_id
     */
    @Override
    public void deleteQueueByRequestId(Long request_id) {
        if (request_id <= 0)
            throw new ResourceNotFoundException("The given id must not be null!");
        QueueRepository.deleteById(request_id);
    }

    private class ExtendedQueue {
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
