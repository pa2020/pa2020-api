package fr.esgi.api.repositories.request;

import fr.esgi.api.models.request.AnalyzeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Repository
public interface AnalyzeRequestRepository extends JpaRepository<AnalyzeRequest,Long> {
    @Query("select a from AnalyzeRequest a where a.requests.request_id=:id")
    Optional<AnalyzeRequest> SearchById(@Param("id") Long id);

}
