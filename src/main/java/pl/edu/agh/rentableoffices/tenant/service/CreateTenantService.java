package pl.edu.agh.rentableoffices.tenant.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.messaging.service.NotificationCreateService;
import pl.edu.agh.rentableoffices.tenant.dao.TenantRepository;
import pl.edu.agh.rentableoffices.tenant.dto.CreateTenantCommand;
import pl.edu.agh.rentableoffices.tenant.exception.TenantNotFoundException;
import pl.edu.agh.rentableoffices.tenant.model.Tenant;
import pl.edu.agh.rentableoffices.tenant.model.TenantBuilder;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CreateTenantService {
    private final TenantRepository repository;
    private final TenantMessageService messageService;
    private final NotificationCreateService notificationCreateService;

    public Long create(CreateTenantCommand command) {
        TenantBuilder tenantBuilder = command.isPrivate()
                ? TenantBuilder.newPrivate(command.getFirstName(), command.getLastName())
                : TenantBuilder.newLegal(command.getCompanyName(), command.getNumberOfEmployees());
        Tenant tenant = tenantBuilder
                .email(command.getEmail())
                .identification(command.getIdType(), command.getIdDocumentNumber())
                .phone(command.getPhoneNumber())
                .preferredMeansOfCommunication(command.getPreferredMeansOfCommunication())
                .build();
        tenant = repository.save(tenant);
        log.info("Tenant {} created", tenant.getFullName());
        try{
            messageService.createMessageForTenant(tenant.getId(), "PLEASE_VERIFY");
        } catch (TenantNotFoundException e) {
            log.error("Tenant not found", e);
        }
        notificationCreateService.createTenantCreatedNotification(tenant.getEmail());
        return tenant.getId();
    }
}
