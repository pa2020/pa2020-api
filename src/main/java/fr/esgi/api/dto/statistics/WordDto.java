package fr.esgi.api.dto.statistics;

import fr.esgi.api.models.statistics.Word;
import fr.esgi.api.repositories.statistics.WordRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Component
@RequiredArgsConstructor
public class WordDto implements IWordDto {
    private final WordRepository wordRepository;

    /**
     * The Logger for this class.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Word> findAll() {
        List<Word> wordList = wordRepository.findAll();
        return wordList;
    }

    @Override
    public Optional<Word> findById(Long id) {
        Optional<Word> search = Optional.of(wordRepository.findById(id)).get();
        if (search.isPresent()) {
            return search;
        } else {
            throw new RuntimeException("Request User_id Introuvable!");
        }
    }

    @Override
    public Word create(Word word) {
        Word search = wordRepository.findByWordIsLike(word.getWord());
        if (wordRepository.existsByWord(word.getWord())) {
            Word wordSave = search;
            int occurence = search.getOccurence() + 1;
            wordSave.setOccurence(occurence++);
            return wordRepository.save(wordSave);
        } else {
            return wordRepository.save(word);
        }
    }
}
