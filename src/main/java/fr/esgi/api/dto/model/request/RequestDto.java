package fr.esgi.api.dto.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import fr.esgi.api.models.request.AnalyzeRequest;
import fr.esgi.api.models.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDto {
    private Long request_id;

    private String setenace;

    private String state;

    private Date created_time;

    private User user;

    private AnalyzeRequest analyzeRequests;
}
