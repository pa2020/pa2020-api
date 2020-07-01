package fr.esgi.api.servicestest;


import fr.esgi.api.models.request.Request;
import fr.esgi.api.repositories.request.RequestRepository;
import fr.esgi.api.repositories.user.UserRepository;
import fr.esgi.api.services.request.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestTest {

    protected RequestRepository requestRepository;
    protected UserRepository userRepository;
    protected RequestService requestService;

    @BeforeEach
    protected void setup() {
        requestRepository = mock(RequestRepository.class);
        userRepository = mock(UserRepository.class);
        requestService = mock(RequestService.class);
    }

    @Test
    void should_Request_empty_after_init() {
        given(requestService.findAll()).willReturn(new ArrayList<>());
        assertTrue(requestService.findAll().isEmpty());
    }

    @Test
    void should_return_AllRequest() {
        ArrayList<Request> requests = new ArrayList<>();
        requests.add(new Request());
        requests.add(new Request());
        requests.add(new Request());

        given(requestService.findAll()).willReturn(requests);
        assertFalse(requests.isEmpty());
        assertEquals(3, requestService.findAll().size());
    }

    @Test
    void should_return_request_with_id() {
        Request request = new Request();
        given(requestService.findById(1L)).willReturn(Optional.of(request));
        assertNotNull(requestService.findById(1L));
    }

    @Test
    void should_return_request_with_userId() {
        ArrayList<Request> requests = new ArrayList<>();
        requests.add(new Request());
        requests.add(new Request());
        requests.add(new Request());

        given(requestService.findRequestByUser_Id(1L)).willReturn(requests);
        assertFalse(requests.isEmpty());
        assertEquals(3, requestService.findRequestByUser_Id(1L).size());
    }

    @Test
    void should_add_new_request_and_returns_new_request() {
        Request request = new Request();
        request.setSentence("trump");

        when(requestService.create(any())).thenReturn(request);
        assertEquals("trump", request.getSentence());
    }

    @Test
    void should_update_request() {
        Request requestTest = new Request();
        requestTest.setRequest_id(1L);

        when(requestService.update(any(), eq(1L))).thenReturn(requestTest);
        Request request = requestService.update(requestTest, requestTest.getRequest_id());
        assertNotNull(request);
        assertEquals(request, requestTest);
    }

    @Test
    void should_delete_throw_exception_request() {
        Request requestTest = new Request();
        requestTest.setRequest_id(1L);
        when(requestService.delete(eq(-1L))).thenThrow(new RuntimeException("error id"));
    }

}
