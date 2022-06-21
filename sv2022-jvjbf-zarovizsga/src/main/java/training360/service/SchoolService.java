package training360.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.DTOs.CreateSchoolCommand;
import training360.DTOs.CreateStudentCommand;
import training360.DTOs.SchoolDto;
import training360.DTOs.StudentDto;
import training360.model.Address;
import training360.model.School;
import training360.repository.SchoolRepository;

@Service
@AllArgsConstructor
public class SchoolService {

    private ModelMapper modelMapper;
    private SchoolRepository schoolRepository;

    public SchoolDto createSchool(CreateSchoolCommand createSchoolCommand) {
//        if (createSchoolCommand.getSchoolName()==null || createSchoolCommand.getSchoolName().isBlank()) {
//            throw new SchoolNameMissingException();
//        }
        Address address = new Address(createSchoolCommand.getPostalCode(), createSchoolCommand.getCity(),
                createSchoolCommand.getStreet(), createSchoolCommand.getHouseNumber());
        School school = new School(createSchoolCommand.getSchoolName(), address);
        schoolRepository.save(school);
        return modelMapper.map(school,SchoolDto.class);
    }


    public StudentDto createStudent(CreateStudentCommand createStudentCommand) {
    }
}
