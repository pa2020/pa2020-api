package fr.esgi.api.dto.mapper;

import fr.esgi.api.dto.model.request.AnalyzeRequestDto;
import fr.esgi.api.dto.model.request.RequestDto;
import fr.esgi.api.models.request.AnalyzeRequest;
import fr.esgi.api.models.request.Request;

/**
 * Created by Zakaria FAHRAOUI.
 */

public class AnalyzeRequestMapper {
    public static AnalyzeRequestDto toAnalyzeRequestDto(AnalyzeRequest analyzeRequest) {
        return new AnalyzeRequestDto()
                .setAnalyze_r_id(analyzeRequest.getAnalyze_r_id())
                .setPositive_comment(analyzeRequest.getPositive_comment())
                .setNegative_comment(analyzeRequest.getNegative_comment())
                .setNeutral_comment(analyzeRequest.getNeutral_comment())
                .setUnanalyzed(analyzeRequest.getUnanalyzed())
                .setRequests(analyzeRequest.getRequests());
    }
}
