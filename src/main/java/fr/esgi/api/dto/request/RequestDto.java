package fr.esgi.api.dto.request;

import fr.esgi.api.models.request.Request;
import fr.esgi.api.models.user.User;
import fr.esgi.api.repositories.request.RequestRepository;
import lombok.*;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;


/**
 * Created by Zakaria FAHRAOUI.
 */

@Component
@RequiredArgsConstructor
public class RequestDto implements IRequestDto{
    private final RequestRepository requestRepository;

    /**
     * The Logger for this class.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Request> findAll() {
        List<Request> requests = requestRepository.findAll();
        return requests;
    }

    @Override
    public Optional<Request> findById(Long id) {
        Optional<Request> request = requestRepository.findById(id);
        return request;
    }

    @Override
    public Request create(Request request) {

        // Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
        if (request.getRequest_id() != null) {
            // Cannot create Request with specified ID value
            return null;
        }

        Request savedRequest = requestRepository.save(request);

        return savedRequest;
    }

    /**
     * Update User Profile
     *
     * @param request
     * @return
     */
    @Override
    public Request update(Request request, Long id) {
        Optional<Request> search = Optional.of(requestRepository.findById(id)).get();

        if (search.isPresent()) {
            Request _request = search.get();
            _request.setUpdate_time(request.getUpdate_time());
            _request.setState(request.getState());
            return requestRepository.save(_request);
        } else {
            throw new RuntimeException("Request Id Introuvable!");
        }
    }

    @Override
    public void delete(Long id) {

        requestRepository.deleteById(id);

    }

}
