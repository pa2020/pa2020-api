package fr.esgi.api.models.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Zakaria FAHRAOUI.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stats")
public class Stats implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int analyze_quantity;
    private float average_feeling;
    private int positive_comment;
    private int negative_comment;
    private int neutral_comment;

}
