package in.exploretech;

import in.exploretech.collection.dto.UserDTO;
import in.exploretech.config.ExternalConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"in.exploretech", "dataman.dmbase.documentutil"})
public class ExploretechApplication {

	public static void main(String[] args) {

		SpringApplication.run(ExploretechApplication.class, args);

		UserDTO userDTO = new UserDTO("krishna", "male");

		System.out.println(userDTO.getGender());
		System.out.println(userDTO.getName());


	}

}
