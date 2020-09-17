package fr.esgi.api.services.queue;

import fr.esgi.api.models.queue.Queue;
import org.springframework.http.ResponseEntity;

/**
 * Created by Zakaria FAHRAOUI.
 */

public interface IQueueService {

    Queue createQueue(Queue queue);

    ResponseEntity<String> getQueueByRequestId(Long request_id);

    void deleteQueueByRequestId(Long request_id);

}
