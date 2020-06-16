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
@AllArgsConstructor
@Entity
@Table(name = "requests")
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long request_id;
    private String sentence;
    private String state;
    private Date created_time;
    private Date update_time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @OneToOne(mappedBy = "requests", fetch = FetchType.LAZY)
    @JsonBackReference("AnalyzeRequest")
    private AnalyzeRequest AnalyzeRequest;
}
