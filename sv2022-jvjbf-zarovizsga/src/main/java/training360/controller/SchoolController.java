package training360.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training360.DTOs.CreateSchoolCommand;
import training360.DTOs.CreateStudentCommand;
import training360.DTOs.SchoolDto;
import training360.DTOs.StudentDto;
import training360.service.SchoolAdministrationService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/schools")
public class SchoolController {

        private SchoolAdministrationService schoolAdministrationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolDto createSchool(@Valid @RequestBody CreateSchoolCommand createSchoolCommand) {
        return schoolAdministrationService.createSchool(createSchoolCommand);
    }

    @PostMapping("/{id}/students")
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolDto createStudent(@PathVariable("id") long id, @Valid @RequestBody CreateStudentCommand createStudentCommand) {
        return schoolAdministrationService.createStudent(id, createStudentCommand);
    }

    @GetMapping
    public List<SchoolDto> getSchools(@RequestParam Optional<String> city) {
        return schoolAdministrationService.getSchools(city);
    }

    @PutMapping("/{id}/students/{stdId}")
    public SchoolDto fireFromSchool(@PathVariable("id") long schoolId, @PathVariable("stdId") long studentId) {
        return schoolAdministrationService.fireFromSchool(schoolId, studentId);
    }



}
