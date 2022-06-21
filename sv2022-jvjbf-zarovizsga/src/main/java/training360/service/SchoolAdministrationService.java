package training360.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.DTOs.CreateSchoolCommand;
import training360.DTOs.CreateStudentCommand;
import training360.DTOs.SchoolDto;
import training360.exceptions.SchoolNotFoundException;
import training360.exceptions.StudentAndSchoolDoesntMatchException;
import training360.exceptions.StudentNotFoundException;
import training360.model.Address;
import training360.model.School;
import training360.model.SchoolAgeStatus;
import training360.model.Student;
import training360.repository.SchoolRepository;
import training360.repository.StudentRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SchoolAdministrationService {

    private ModelMapper modelMapper;
    private SchoolRepository schoolRepository;

    private StudentRepository studentRepository;

    public SchoolDto createSchool(CreateSchoolCommand createSchoolCommand) {
//        if (createSchoolCommand.getSchoolName()==null || createSchoolCommand.getSchoolName().isBlank()) {
//            throw new SchoolNameMissingException();
//        }
        Address address = new Address(createSchoolCommand.getPostalCode(), createSchoolCommand.getCity(),
                createSchoolCommand.getStreet(), createSchoolCommand.getHouseNumber());
        School school = new School(createSchoolCommand.getSchoolName(), address);
        schoolRepository.save(school);
        return modelMapper.map(school, SchoolDto.class);
    }

    @Transactional
    public SchoolDto createStudent(long id, CreateStudentCommand createStudentCommand) {
        School school = schoolRepository.findById(id).orElseThrow(() -> new SchoolNotFoundException(id));
        Student student = new Student(createStudentCommand.getName(), createStudentCommand.getDateOfBirth(),
                getSchoolStatus(createStudentCommand.getDateOfBirth()));
        studentRepository.save(student);
        school.addStudent(student);
        return modelMapper.map(school, SchoolDto.class);
    }

    public List<SchoolDto> getSchools(Optional<String> city) {
        return schoolRepository.findByParams(city).stream()
                .map(school -> modelMapper.map(school, SchoolDto.class))
                .collect(Collectors.toList());
    }

    public SchoolDto fireFromSchool(long schoolId, long studentId) {
        School school = schoolRepository.findById(schoolId).orElseThrow(() -> new SchoolNotFoundException(schoolId));
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
        if (student.getSchool().equals(school)) {
            student.setSchool(null);
            schoolRepository.save(school);
        } else {
            throw new StudentNotFoundException(studentId);
        }
        School schoolReLoad = schoolRepository.findById(schoolId).orElseThrow(() -> new SchoolNotFoundException(schoolId));
        return modelMapper.map(schoolReLoad, SchoolDto.class);
    }

    private SchoolAgeStatus getSchoolStatus(LocalDate dateOfBirth) {
        return ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now() ) < 16 ?
                SchoolAgeStatus.SCHOOL_AGED : SchoolAgeStatus.NOT_SCHOOL_AGED;
    }
}
