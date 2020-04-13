package fr.esgi.api.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.esgi.api.DTO.GetSampleDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sample")
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sampleId;

    @NotNull
    private String value;

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tags",
            joinColumns = @JoinColumn(name = "sample_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    public GetSampleDTO toResponse() {
        return GetSampleDTO.builder()
                .name(value)
                .build();
    }
}
