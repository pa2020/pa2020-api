package fr.esgi.api.dto.mapper;

import fr.esgi.api.dto.model.request.RequestDto;
import fr.esgi.api.models.request.Request;


/**
 * Created by Zakaria FAHRAOUI.
 */

public class RequestMapper {
    public static RequestDto toRequestDto(Request request) {
        return new RequestDto()
                .setRequest_id(request.getRequest_id())
                .setSetenace(request.getSetenace())
                .setAnalyzeRequests(request.getAnalyzeRequests())
                .setState(request.getState())
                .setCreated_time(request.getCreated_time())
                .setUser(request.getUser());
    }
}
