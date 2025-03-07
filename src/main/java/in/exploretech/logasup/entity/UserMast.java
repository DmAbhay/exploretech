package in.exploretech.logasup.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMast {

    @Id
    @GeneratedValue(generator = "exploreTechIdGenerator")
    @GenericGenerator(name = "exploreTechIdGenerator", strategy = "in.exploretech.util.GenerateIdForUserMast")
    private String id;

    @Column(unique = true) // Ensures the username is unique in the table
    private String username;
    private String password;



}
