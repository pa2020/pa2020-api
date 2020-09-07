package fr.esgi.api.repositories.queue;


import fr.esgi.api.models.queue.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Alexis DESJARDINS.
 */

@Repository
public interface QueueRepository extends JpaRepository<Queue, Long> {
    @Query("select q from Queue q where q.user.user_id=:id")
    List<Queue> listRequestByUserId(@Param("id") Long id);
}
