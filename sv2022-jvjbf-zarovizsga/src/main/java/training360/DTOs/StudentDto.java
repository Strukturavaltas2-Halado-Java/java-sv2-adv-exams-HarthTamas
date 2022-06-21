package training360.DTOs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import training360.model.School;
import training360.model.SchoolAgeStatus;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDto {

    private String name;
    private Long id;
    private LocalDate dateOfBirth;
    private SchoolAgeStatus schoolAgeStatus;

}
