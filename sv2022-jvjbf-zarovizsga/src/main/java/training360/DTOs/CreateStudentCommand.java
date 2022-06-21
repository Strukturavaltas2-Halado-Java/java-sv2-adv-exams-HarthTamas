package training360.DTOs;

import training360.model.School;
import training360.model.SchoolAgeStatus;

import java.time.LocalDate;

public class CreateStudentCommand {

    private LocalDate dateOfBirth;
    private SchoolAgeStatus schoolAgeStatus;
    private School school;
}
