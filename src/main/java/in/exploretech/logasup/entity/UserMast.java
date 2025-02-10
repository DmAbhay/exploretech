package in.exploretech.logasup.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMast {

    @Id
    private String id;
    private String userName;
    private String password;

    private String generateCustomId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
        String timestamp = sdf.format(new Date());
        return "EXPLOREABC_" + timestamp;
    }

}
