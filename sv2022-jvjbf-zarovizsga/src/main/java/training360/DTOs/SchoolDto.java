package training360.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import training360.model.Address;
import training360.model.Student;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SchoolDto {

    private Long id;
    private String schoolName;
    private Address address;
    private List<StudentDto> students = new ArrayList<>();
}
