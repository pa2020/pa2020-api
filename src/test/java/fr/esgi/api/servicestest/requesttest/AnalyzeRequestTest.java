package fr.esgi.api.servicestest.requesttest;

import fr.esgi.api.models.request.AnalyzeRequest;
import fr.esgi.api.services.request.AnalyzeRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnalyzeRequestTest {

    protected AnalyzeRequestService analyzeRequestService;


    @BeforeEach
    protected void setup() {
        analyzeRequestService = mock(AnalyzeRequestService.class);
    }

    @Test
    void should_create_request_and_returns_request() {
        AnalyzeRequest analyzeRequest = new AnalyzeRequest();
        analyzeRequest.setNegative(19.345676543);
        analyzeRequest.setPositive(4.28767890987);
        analyzeRequest.setNeutral(37.098765673);
        analyzeRequest.setUnanalyzed(0);

        when(analyzeRequestService.create(any())).thenReturn(analyzeRequest);
        assertEquals(37.098765673, analyzeRequest.getNeutral());
    }
}
