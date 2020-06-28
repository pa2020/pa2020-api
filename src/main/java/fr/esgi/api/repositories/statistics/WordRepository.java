package fr.esgi.api.repositories.statistics;

import fr.esgi.api.models.statistics.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    Word findByWordIsLike(String word);

    Boolean existsByWord(String word);
}
