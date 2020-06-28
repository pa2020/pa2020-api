package fr.esgi.api.dto.statistics;

import fr.esgi.api.models.statistics.Word;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zakaria FAHRAOUI.
 */

public interface IWordDto {

    List<Word> findAll();

    Optional<Word> findById(Long id);

    Word create(Word word);

}
