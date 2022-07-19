package sportresults.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "result_date")
    private LocalDate resultDate;

    private double measure;

    @Column(name = "measure_unit")
    private char measureUnit;

    @ManyToOne
    @JoinColumn(name = "athlete_id")
    @JsonBackReference
    private Athlete athlete;


}
