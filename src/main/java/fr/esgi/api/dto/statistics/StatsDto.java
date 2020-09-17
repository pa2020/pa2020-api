package fr.esgi.api.dto.statistics;

import fr.esgi.api.models.statistics.Stats;
import fr.esgi.api.models.statistics.Word;
import fr.esgi.api.repositories.statistics.StatsRepository;
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
public class StatsDto implements IStatsDto {
    private final StatsRepository statsRepository;
    private final WordRepository wordRepository;

    @Override
    public List<Stats> listStatsOfWord(String word) {
        Optional<Word> search = Optional.ofNullable(wordRepository.findByWordIsLike(word));
        if (search.isPresent()) {
            return statsRepository.listStatsOfWord(word);
        } else {
            throw new RuntimeException("Word Not found!");
        }
    }

    @Override
    public Stats create(Stats stats) {
        if (stats.getId() != null) {
            // Cannot create Request with specified ID value
            return null;
        } else {
            return statsRepository.save(stats);
        }
    }
}
