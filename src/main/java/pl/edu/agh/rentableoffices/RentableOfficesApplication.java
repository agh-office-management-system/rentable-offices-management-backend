package pl.edu.agh.rentableoffices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RentableOfficesApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RentableOfficesApplication.class, args);
	}

}
