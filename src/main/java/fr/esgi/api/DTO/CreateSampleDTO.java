package fr.esgi.api.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateSampleDTO {

    @NotNull
    private String value;

    private List<String> tags;

    public boolean isValid() {
        return true;
    }
}
