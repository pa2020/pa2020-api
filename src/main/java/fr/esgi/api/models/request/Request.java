package fr.esgi.api.models.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.esgi.api.models.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
@Table(name = "requests")
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long request_id;
    @NonNull
    private String sentence;
    @NonNull
    private Date created_time;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinTable(name = "request_state", joinColumns = @JoinColumn(name = "request_id"), inverseJoinColumns = @JoinColumn(name = "state_id"))
    private Set<State> states = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @OneToOne
    @JoinColumn(name = "analyze_request_id")
    @JsonBackReference
    private AnalyzeRequest analyzeRequests;
}
