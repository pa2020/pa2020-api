package fr.esgi.api.services.queue;

import fr.esgi.api.dto.queue.QueueDto;
import fr.esgi.api.models.queue.Queue;
import fr.esgi.api.services.queue.IQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by Alexis DESJARDINS.
 */

@Service
@RequiredArgsConstructor
public class QueueService implements IQueueService {
    private final QueueDto queueDto;

    @Transactional(readOnly = true)
    @Override
    public String createQueue(Queue queue) {
        return queueDto.createQueue(queue);
    }

    @Transactional
    @Override
    public Queue getQueueByRequestId(Long request_id) {
        return queueDto.getQueueByRequestId(request_id);
    }

    @Transactional
    @Override
    public void deleteQueueByRequestId(Long request_id) {
        queueDto.deleteQueueByRequestId(request_id);
    }
}
