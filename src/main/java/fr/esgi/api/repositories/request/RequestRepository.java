package fr.esgi.api.repositories.request;

import fr.esgi.api.models.request.Request;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Zakaria FAHRAOUI.
 */

public interface RequestRepository extends JpaRepository<Request, Long> {
}
