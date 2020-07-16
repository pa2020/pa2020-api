package fr.esgi.api.servicestest.requesttest;

import fr.esgi.api.services.request.BlackListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class BlackListTest {
    protected BlackListService blackListService;

    @BeforeEach
    protected void setup() {
        blackListService = mock(BlackListService.class);
    }

    @Test
    void should_black_list_empty_after_init() {
        given(blackListService.AllBlackList()).willReturn(new ArrayList<>());
        assertTrue(blackListService.AllBlackList().isEmpty());
    }

}
