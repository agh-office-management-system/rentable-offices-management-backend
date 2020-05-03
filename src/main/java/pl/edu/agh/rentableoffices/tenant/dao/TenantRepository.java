package pl.edu.agh.rentableoffices.tenant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.rentableoffices.tenant.exception.TenantNotFoundException;
import pl.edu.agh.rentableoffices.tenant.model.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

    default Tenant get(Long id) throws TenantNotFoundException {
        return findById(id).orElseThrow(() -> new TenantNotFoundException(id));
    }
}
