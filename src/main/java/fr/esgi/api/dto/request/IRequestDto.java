package fr.esgi.api.dto.request;

import fr.esgi.api.models.request.Request;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zakaria FAHRAOUI.
 */
public interface IRequestDto {
    List<Request> findAll();

    Optional<Request> findById(Long id);

    Request create(Request request);

    Request update(Request request);

    void delete(Long id);
}
