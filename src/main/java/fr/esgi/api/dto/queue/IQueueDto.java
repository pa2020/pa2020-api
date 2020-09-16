package fr.esgi.api.dto.queue;

import fr.esgi.api.models.queue.Queue;
import org.springframework.http.ResponseEntity;

/**
 * Created by Zakaria FAHRAOUI.
 */
public interface IQueueDto {

    Queue createQueue(Queue queue);

    ResponseEntity<String> getQueueByRequestId(Long user_id);

    void deleteQueueByRequestId(Long request_id);
}
