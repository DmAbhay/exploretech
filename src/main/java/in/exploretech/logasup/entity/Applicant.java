package in.exploretech.logasup.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Applicant {

    @Id
    @GeneratedValue(generator = "exploreTechIdGenerator")
    @GenericGenerator(name = "exploreTechIdGenerator", strategy = "in.exploretech.util.GenerateIdForApplicant")
    private String id;

    @Column(unique = true) // Ensures the username is unique in the table
    private String username;
    private String password;
    private Integer age;
    private String gender;
    private String address;
    private String email;

}
