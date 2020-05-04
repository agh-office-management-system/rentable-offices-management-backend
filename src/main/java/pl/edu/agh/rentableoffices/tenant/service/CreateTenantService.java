package pl.edu.agh.rentableoffices.tenant.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.tenant.dao.TenantRepository;
import pl.edu.agh.rentableoffices.tenant.dto.CreateTenantCommand;
import pl.edu.agh.rentableoffices.tenant.exception.TenantNotFoundException;
import pl.edu.agh.rentableoffices.tenant.model.Tenant;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CreateTenantService {
    private final TenantRepository repository;
    private final TenantMessageService messageService;

    //TODO - Informacja o próbie utworzenia nowego profilu trafia do najemcy, który weryfikuje poprawność wprowadzonych informacji
    public Long create(CreateTenantCommand command) {
        //TODO - Dorobić Builder
        Tenant tenant = Tenant.create(command.getFirstName(), command.getLastName(), command.isPrivate(),
                command.getIdType(), command.getIdDocumentNumber(), command.getPreferredMeansOfCommunication(),
                command.getPhoneNumber(), command.getEmail(), command.getLogin());
        tenant = repository.save(tenant);
        log.info("Tenant {} created", tenant.getEmail());
        try{
            messageService.createMessageForTenant(tenant.getId(), "PLEASE_VERIFY");
        } catch (TenantNotFoundException e) {
            e.printStackTrace();
        }
        return tenant.getId();
    }
}
