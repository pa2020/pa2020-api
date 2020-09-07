package fr.esgi.api.services.queue;

import fr.esgi.api.dto.queue.QueueDto;
import fr.esgi.api.models.queue.Queue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Alexis DESJARDINS.
 */

@Service
@RequiredArgsConstructor
public class QueueService implements IQueueService {
    private final QueueDto queueDto;

    @Override
    public Queue createQueue(Queue queue) {
        return queueDto.createQueue(queue);
    }

    @Transactional(readOnly = true)
    @Override
    public String getQueueByRequestId(Long request_id) {
        return queueDto.getQueueByRequestId(request_id);
    }

    @Transactional
    @Override
    public void deleteQueueByRequestId(Long request_id) {
        queueDto.deleteQueueByRequestId(request_id);
    }
}
