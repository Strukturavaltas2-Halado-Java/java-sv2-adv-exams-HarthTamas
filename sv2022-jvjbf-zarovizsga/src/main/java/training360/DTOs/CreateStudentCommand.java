package training360.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import training360.model.School;
import training360.model.SchoolAgeStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentCommand {

    public CreateStudentCommand(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    @NotNull
    @NotBlank
    private String name;

    @Past
    private LocalDate dateOfBirth;
    private SchoolAgeStatus schoolAgeStatus;
    private School school;

}
