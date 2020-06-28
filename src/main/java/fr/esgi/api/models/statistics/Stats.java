package fr.esgi.api.models.statistics;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = "stats")
public class Stats implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int analyze_quantity;
    private float average_feeling;
    private double positive_comment;
    private double negative_comment;
    private double neutral_comment;
    private Date created_time;

    @OneToMany(mappedBy = "stats", fetch = FetchType.LAZY)
    @JsonBackReference("words")
    private Set<Word> words = new HashSet<>();
}
