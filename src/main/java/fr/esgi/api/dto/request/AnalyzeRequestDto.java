package fr.esgi.api.dto.request;

import fr.esgi.api.models.request.AnalyzeRequest;
import fr.esgi.api.models.request.Request;
import fr.esgi.api.repositories.request.AnalyzeRequestRepository;
import fr.esgi.api.repositories.request.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Component
@RequiredArgsConstructor
public class AnalyzeRequestDto implements IAnalyzeRequestDto {
    private final AnalyzeRequestRepository analyzeRequestRepository;
    private final RequestRepository requestRepository;


    @Override
    public AnalyzeRequest create(AnalyzeRequest analyzeRequest) {
        Optional<Request> search = Optional.of(requestRepository.findById(analyzeRequest.getRequests().getRequest_id())).get();
        Optional<AnalyzeRequest> search2 = Optional.of(analyzeRequestRepository.SearchById(analyzeRequest.getRequests().getRequest_id())).get();
        if (analyzeRequest.getAnalyze_r_id() != null) {
            // Cannot create Request with specified ID value
            return null;
        } else if (search2.isPresent()) {
            throw new RuntimeException("Request_Id already linked for an Analyze_r_id! == > id = " + analyzeRequest.getRequests().getRequest_id());
        } else if (search.isEmpty()) {
            throw new RuntimeException("Request Id Introuvable! == > id = " + analyzeRequest.getRequests().getRequest_id());
        } else {
            return analyzeRequestRepository.save(analyzeRequest);
        }
    }
}
