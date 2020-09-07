package fr.esgi.api.dto.queue;

import fr.esgi.api.models.queue.Queue;

/**
 * Created by Zakaria FAHRAOUI.
 */
public interface IQueueDto {

    Queue createQueue(Queue queue);

    String getQueueByRequestId(Long user_id);

    void deleteQueueByRequestId(Long request_id);
}
