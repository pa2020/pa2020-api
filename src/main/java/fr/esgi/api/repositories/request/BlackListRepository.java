package fr.esgi.api.repositories.request;

import fr.esgi.api.models.request.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Long> {
    @Query("select b from BlackList b where b.sentence=:x")
    List<BlackList> listOfSentence(@Param("x") String msg);
}
