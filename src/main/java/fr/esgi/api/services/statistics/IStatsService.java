package fr.esgi.api.services.statistics;

import fr.esgi.api.models.statistics.Stats;

import java.util.List;

/**
 * Created by Zakaria FAHRAOUI.
 */

public interface IStatsService {

    List<Stats> listStatsOfWord(String word);

    Stats create(Stats stats);
}
