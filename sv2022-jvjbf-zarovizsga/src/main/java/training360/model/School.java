package training360.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="school")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="school_name")
    private String schoolName;

    @Embedded
    @AttributeOverride(name="postalCode", column=@Column(name="postalCode"))
    @AttributeOverride(name="city", column=@Column(name="city"))
    @AttributeOverride(name="street", column=@Column(name="street"))
    @AttributeOverride(name="houseNumber", column=@Column(name="house_nr"))
    private Address address;

    @OneToMany(mappedBy = "school", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Student> students = new ArrayList<>();

    public School(String schoolName, Address address) {
        this.schoolName = schoolName;
        this.address = address;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.setSchool(this);
    }

}
