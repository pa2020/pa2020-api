package fr.esgi.api.models.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "analyze_requests")
public class AnalyzeRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long analyze_r_id;
    @NonNull
    private Long positive_comment;
    @NonNull
    private Long negative_comment;
    @NonNull
    private Long neutral_comment;
    @NonNull
    private Long unanalyzed;

    @OneToOne
    @JoinColumn(name = "requests_id")
    @JsonBackReference("requests")
    private Request requests;
}
