package fr.esgi.api.services.statistics;


import fr.esgi.api.dto.statistics.WordDto;
import fr.esgi.api.models.statistics.Word;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Service
@RequiredArgsConstructor
public class WordService implements IWordService {
    private final WordDto wordDto;

    @Override
    @Transactional(readOnly = true)
    public List<Word> findAll() {
        return wordDto.findAll();
    }

    @Override
    @Transactional
    public Optional<Word> findById(Long id) {
        return wordDto.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Word create(Word word) {
        return wordDto.create(word);
    }
}
