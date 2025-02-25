package in.exploretech.logasup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMastDTO {

    private String username;
    private String password;
    private Integer age;
    private String gender;
    private String address;
    private String email;
}