package fr.esgi.api.models.queue;

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
    private Long request_id;
    private int position;
    private int total;
}
