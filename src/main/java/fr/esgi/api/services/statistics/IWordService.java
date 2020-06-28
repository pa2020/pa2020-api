package fr.esgi.api.services.statistics;

import fr.esgi.api.models.statistics.Word;

import java.util.List;
import java.util.Optional;

public interface IWordService {

    List<Word> findAll();

    Optional<Word> findById(Long id);

    Word create(Word word);
}
