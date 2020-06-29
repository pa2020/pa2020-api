package fr.esgi.api.models.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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
    private double positive;
    @NonNull
    private double negative;
    @NonNull
    private double neutral;
    @NonNull
    private double unanalyzed;


    @OneToOne
    @JoinColumn(name = "requests_id")
    @JsonBackReference("requests")
    private Request requests;
}
