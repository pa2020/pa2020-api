package fr.esgi.api.models.queue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.esgi.api.models.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Alexis DESJARDINS.
 */

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "queue")
public class Queue implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "request_id")
    private Long requestId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;
}
