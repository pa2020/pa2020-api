package fr.esgi.api.dto.queue;

import fr.esgi.api.models.queue.Queue;

/**
 * Created by Zakaria FAHRAOUI.
 */
public interface IQueueDto {

    String createQueue(Queue queue);

    Queue getQueueByRequestId(Long request_id);

    void deleteQueueByRequestId(Long request_id);
}
