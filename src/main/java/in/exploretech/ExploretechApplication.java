package in.exploretech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"in.exploretech", "dataman.dmbase.documentutil"})
public class ExploretechApplication {

	public static void main(String[] args) {

		SpringApplication.run(ExploretechApplication.class, args);





	}

}
