package fr.esgi.api.servicestest.statisticstest;

import fr.esgi.api.models.statistics.Stats;
import fr.esgi.api.services.statistics.StatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatsTest {

    protected StatsService statsService;

    @BeforeEach
    protected void setup() {
        statsService = mock(StatsService.class);
    }

    @Test
    void should_stats_empty_after_init() {
        given(statsService.listStatsOfWord("")).willReturn(new ArrayList<>());
        assertTrue(statsService.listStatsOfWord("").isEmpty());
    }

    @Test
    void should_return_list_stats() {
        List<Stats> stats = new ArrayList<>();
        when(statsService.listStatsOfWord("trump")).thenReturn(stats);
        assertTrue(stats.isEmpty());
        assertNotNull(stats);
    }

    @Test
    void should_create_stats_and_returns_stats() {
        Stats stats = new Stats();
        stats.setAnalyze_quantity(10);
        stats.setAverage_feeling((float) 0.2);
        stats.setNegative_comment(0.54);
        stats.setNeutral_comment(0.22);
        stats.setPositive_comment(0.122);
        stats.setPositive_comment(0.66);
        when(statsService.create(any())).thenReturn(stats);
        assertEquals(10, stats.getAnalyze_quantity());
    }

}
