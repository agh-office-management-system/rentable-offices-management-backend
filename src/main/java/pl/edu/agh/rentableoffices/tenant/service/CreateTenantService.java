package pl.edu.agh.rentableoffices.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.tenant.dto.CreateTenantCommand;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateTenantService {

    //TODO - Informacja o próbie utworzenia nowego profilu trafia do najemcy, który weryfikuje poprawność wprowadzonych informacji
    public Long create(CreateTenantCommand command) {
        //TODO
        return 1L;
    }
}
