package pl.edu.agh.rentableoffices.tenant.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.messaging.service.NotificationCreateService;
import pl.edu.agh.rentableoffices.tenant.dao.TenantRepository;
import pl.edu.agh.rentableoffices.tenant.dto.UpdateTenantCommand;
import pl.edu.agh.rentableoffices.tenant.dto.VerifyTenantCommand;
import pl.edu.agh.rentableoffices.tenant.exception.TenantNotFoundException;
import pl.edu.agh.rentableoffices.tenant.model.Tenant;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TenantUpdateService {
    private final TenantRepository repository;
    private final NotificationCreateService notificationCreateService;

    public void update(@NotNull Long id, UpdateTenantCommand command) throws TenantNotFoundException {
        //TODO
    }

    public void verify(@NotNull Long id, VerifyTenantCommand command) throws TenantNotFoundException {
        Tenant tenant = repository.get(id);
        if(command.isAccepted()) {
            tenant.verify();
            log.info("Tenant {} verified", tenant.getFullName());
            notificationCreateService.createTenantVerifiedNotification(tenant.getEmail());
        } else {
            tenant.reject(command.getRejectionReason());
            log.info("Tenant {} rejected", tenant.getFullName());
            notificationCreateService.createTenantRejectedNotification(tenant.getEmail(), command.getRejectionReason());
        }
    }
}
