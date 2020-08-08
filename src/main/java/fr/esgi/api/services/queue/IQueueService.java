package fr.esgi.api.services.queue;
import fr.esgi.api.models.queue.Queue;

import java.util.List;

/**
 * Created by Alexis DESJARDINS.
 */

public interface IQueueService {

    String createQueue(Queue queue);

    Queue getQueueByRequestId(Long request_id);

    void deleteQueueByRequestId(Long request_id);

}
