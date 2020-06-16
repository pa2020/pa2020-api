package fr.esgi.api.repositories.request;

import fr.esgi.api.models.request.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("select r from Request r where r.user.user_id=:id")
    List<Request> listRequestByUser_id(@Param("id") Long id);
}
