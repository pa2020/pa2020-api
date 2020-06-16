package fr.esgi.api.services;

import fr.esgi.api.models.request.Request;

import java.util.List;
import java.util.Optional;

public interface IRequestService {

    List<Request> findAll();

    Optional<Request> findById(Long id);

    List<Request> findRequestByUser_Id(Long id);

    Request create(Request request);

    Request update(Request request, Long id);

    void delete(Long id);

}
