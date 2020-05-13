package fr.esgi.api.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import fr.esgi.api.models.request.Request;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
public class AnalyzeRequestDto {
    private Long analyze_r_id;

    private Long positive_comment;

    private Long negative_comment;

    private Long neutral_comment;

    private Long unanalyzed;

    private Request requests;
}
