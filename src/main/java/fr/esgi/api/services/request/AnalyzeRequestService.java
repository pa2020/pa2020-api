package fr.esgi.api.services.request;

import fr.esgi.api.dto.request.AnalyzeRequestDto;
import fr.esgi.api.models.request.AnalyzeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Service
@RequiredArgsConstructor
public class AnalyzeRequestService implements IAnalyzeRequestService {
    private final AnalyzeRequestDto analyzeRequestDto;

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    public AnalyzeRequest create(AnalyzeRequest analyzeRequest) {
        return analyzeRequestDto.create(analyzeRequest);
    }
}
