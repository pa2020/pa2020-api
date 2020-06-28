package fr.esgi.api.repositories.statistics;

import fr.esgi.api.models.statistics.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Repository
public interface StatsRepository extends JpaRepository<Stats, Long> {
}
