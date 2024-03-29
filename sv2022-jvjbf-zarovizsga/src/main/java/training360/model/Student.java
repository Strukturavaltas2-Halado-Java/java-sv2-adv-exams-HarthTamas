package training360.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name="school_age_status")
    private SchoolAgeStatus schoolAgeStatus;

    public Student(String name, LocalDate dateOfBirth, SchoolAgeStatus schoolAgeStatus) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.schoolAgeStatus = schoolAgeStatus;
    }

    @ManyToOne
    @JoinColumn(name = "school_id")
    @JsonBackReference
    private School school;


}