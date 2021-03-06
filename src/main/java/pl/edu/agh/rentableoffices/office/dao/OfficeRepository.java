package pl.edu.agh.rentableoffices.office.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.rentableoffices.common.Address;
import pl.edu.agh.rentableoffices.office.exception.OfficeNotFoundException;
import pl.edu.agh.rentableoffices.office.model.Office;

public interface OfficeRepository extends JpaRepository<Office, Long> {

    boolean existsByAddress(Address address);

    default Office get(Long id) throws OfficeNotFoundException {
        return findById(id).orElseThrow(() -> new OfficeNotFoundException(id));
    }

}
