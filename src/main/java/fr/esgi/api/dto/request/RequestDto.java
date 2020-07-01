package fr.esgi.api.dto.request;

import fr.esgi.api.exception.ResourceNotFoundException;
import fr.esgi.api.models.request.Request;
import fr.esgi.api.models.user.User;
import fr.esgi.api.repositories.request.RequestRepository;
import fr.esgi.api.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


/**
 * Created by Zakaria FAHRAOUI.
 */

@Component
@RequiredArgsConstructor
public class RequestDto implements IRequestDto {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;


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
    public List<Request> findByUser_Id(Long id) {
        Optional<User> search = Optional.of(userRepository.findById(id)).get();
        if (search.isPresent()) {
            return requestRepository.listRequestByUser_id(id);
        } else {
            throw new RuntimeException("Request User_id Introuvable!");
        }
    }

    @Override
    public Request create(Request request) {

        Optional<User> search = Optional.of(userRepository.findById(request.getUser().getUser_id())).get();

        if (search.isPresent()) {
            request.setRequest_id(request.getRequest_id());
            request.setCreated_time(request.getCreated_time());
            request.setState(request.getState());
            request.setUser(request.getUser());
            return requestRepository.save(request);
        } else {
            throw new RuntimeException("Request User_Id Introuvable!");
        }
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
        if (id <= 0)
            throw new ResourceNotFoundException("The given id must not be null!");
        requestRepository.deleteById(id);
    }

}
