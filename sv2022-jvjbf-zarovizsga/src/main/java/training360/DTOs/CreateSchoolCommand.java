package training360.DTOs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CreateSchoolCommand {

    @NotBlank
    @NotNull
    private String schoolName;

    private String postalCode;
    private  String city;
    private  String street;
    private  int houseNumber;

}
