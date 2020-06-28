package fr.esgi.api.repositories.statistics;

import fr.esgi.api.models.statistics.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Repository
public interface StatsRepository extends JpaRepository<Stats, Long> {

    @Query("select s,w from Stats s, Word w where s.words.id=w.id and w.word=:word")
    List<Stats> listStatsOfWord(@Param("word") String word);
}
