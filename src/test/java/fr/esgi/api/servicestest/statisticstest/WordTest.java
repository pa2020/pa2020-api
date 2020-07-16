package fr.esgi.api.servicestest.statisticstest;

import fr.esgi.api.models.statistics.Word;
import fr.esgi.api.services.statistics.WordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WordTest {
    protected WordService wordService;

    @BeforeEach
    protected void setup() {
        wordService = mock(WordService.class);
    }

    @Test
    void should_word_empty_after_init() {
        given(wordService.findAll()).willReturn(new ArrayList<>());
        assertTrue(wordService.findAll().isEmpty());
    }

    @Test
    void should_return_all_word() {
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word());
        words.add(new Word());
        words.add(new Word());

        given(wordService.findAll()).willReturn(words);
        assertFalse(words.isEmpty());
        assertEquals(3, wordService.findAll().size());
    }

    @Test
    void should_return_word_with_id() {
        Word word = new Word();
        given(wordService.findById(1L)).willReturn(Optional.of(word));
        assertNotNull(wordService.findById(1L));
    }

    @Test
    void should_create_word_and_returns_word() {
        Word word = new Word();
        word.setWord("test word");
        when(wordService.create(any())).thenReturn(word);
        assertEquals("test word", word.getWord());
    }

}
