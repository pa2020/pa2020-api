package fr.esgi.api.repositories.request;

import fr.esgi.api.models.request.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
