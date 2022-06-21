package training360.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Address {

    private String postalCode;
    private  String city;
    private  String street;
    private  int houseNumber;

}
