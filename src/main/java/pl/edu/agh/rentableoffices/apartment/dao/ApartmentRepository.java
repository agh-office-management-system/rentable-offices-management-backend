package pl.edu.agh.rentableoffices.apartment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.rentableoffices.apartment.model.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
