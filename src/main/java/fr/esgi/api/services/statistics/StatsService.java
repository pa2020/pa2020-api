package fr.esgi.api.services.statistics;

import fr.esgi.api.dto.statistics.StatsDto;
import fr.esgi.api.models.statistics.Stats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Service
@RequiredArgsConstructor
public class StatsService implements IStatsService {

    private final StatsDto statsDto;

    @Override
    public List<Stats> listStatsOfWord(String word) {
        return statsDto.listStatsOfWord(word);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Stats create(Stats stats) {
        return statsDto.create(stats);
    }
}
