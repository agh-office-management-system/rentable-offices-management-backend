package pl.edu.agh.rentableoffices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
public class RentableOfficesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentableOfficesApplication.class, args);
	}

}
