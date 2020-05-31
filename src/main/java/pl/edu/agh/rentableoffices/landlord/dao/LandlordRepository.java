package pl.edu.agh.rentableoffices.landlord.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.rentableoffices.landlord.model.Landlord;
import pl.edu.agh.rentableoffices.tenant.exception.LandlordNotFoundException;
import pl.edu.agh.rentableoffices.tenant.exception.TenantNotFoundException;
import pl.edu.agh.rentableoffices.tenant.model.Tenant;

public interface LandlordRepository extends JpaRepository<Landlord, Long> {

    default Landlord get(Long id) throws LandlordNotFoundException {
        return findById(id).orElseThrow(() -> new LandlordNotFoundException(id));
    }

    Landlord getByEmail(String email);
}
