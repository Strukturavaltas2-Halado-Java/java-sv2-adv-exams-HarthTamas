package training360.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training360.DTOs.CreateSchoolCommand;
import training360.DTOs.CreateStudentCommand;
import training360.DTOs.SchoolDto;
import training360.DTOs.StudentDto;
import training360.service.SchoolService;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/schools")
public class SchoolController {

        private SchoolService schoolService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolDto createSchool(@Valid @RequestBody CreateSchoolCommand createSchoolCommand) {
        return schoolService.createSchool(createSchoolCommand);
    }

    @PostMapping("/{id}/students")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto createStudent(@PathVariable("id") long id, @Valid @RequestBody CreateStudentCommand createStudentCommand) {
        return schoolService.createStudent(id, createStudentCommand);
    }



}
