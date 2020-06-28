package fr.esgi.api.dto.statistics;

import fr.esgi.api.models.statistics.Stats;

import java.util.List;

/**
 * Created by Zakaria FAHRAOUI.
 */

public interface IStatsDto {

    List<Stats> listStatsOfWord(String word);

    Stats create(Stats stats);
}
