package fr.esgi.api.repositories.queue;


import fr.esgi.api.models.queue.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Alexis DESJARDINS.
 */

@Repository
public interface QueueRepository extends JpaRepository<Queue, Long> {
    Optional<Queue> findByRequestId(Queue request_id);
}
