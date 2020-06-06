package fr.esgi.api.services;


import fr.esgi.api.dto.request.RequestDto;
import fr.esgi.api.models.request.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Service
@RequiredArgsConstructor
public class RequestService implements IRequestService{
    private final RequestDto requestDto;

    @Override
    public Boolean send(Request request) {
        return requestDto.send(request);
    }

    @Override
    public void sendAsync(Request request) {
        requestDto.sendAsync(request);
    }

    @Override
    public Future<Boolean> sendAsyncWithResult(Request request) {
        return requestDto.sendAsyncWithResult(request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> findAll() {
        return requestDto.findAll();
    }

    @Override
    @Transactional
    public Optional<Request> findById(Long id) {
        return requestDto.findById(id);
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    public Request create(Request request) {
        return requestDto.create(request);
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    public Request update(Request request) {
        return requestDto.update(request);
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    public void delete(Long id) {
        requestDto.delete(id);
    }

}