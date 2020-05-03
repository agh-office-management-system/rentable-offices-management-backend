package pl.edu.agh.rentableoffices.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.messaging.model.NotificationType;
import pl.edu.agh.rentableoffices.messaging.service.NotificationService;
import pl.edu.agh.rentableoffices.tenant.dao.TenantRepository;
import pl.edu.agh.rentableoffices.tenant.dto.UpdateTenantCommand;
import pl.edu.agh.rentableoffices.tenant.dto.VerifyTenantCommand;
import pl.edu.agh.rentableoffices.tenant.exception.TenantNotFoundException;
import pl.edu.agh.rentableoffices.tenant.model.Tenant;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
@Transactional
@RequiredArgsConstructor
public class TenantUpdateService {
    private final TenantRepository repository;
    private final NotificationService notificationService;

    public void update(@NotNull Long id, UpdateTenantCommand command) throws TenantNotFoundException {

    }

    public void verify(@NotNull Long id, VerifyTenantCommand command) throws TenantNotFoundException {
        Tenant tenant = repository.get(id);
        if(command.isAccepted()) {
            tenant.verify();
            notificationService.notifyAdministration(NotificationType.TENANT_VERIFIED, new Object[]{id});
        } else {
            tenant.reject(command.getRejectionReason());
            notificationService.notifyAdministration(NotificationType.TENANT_REJECTED, new Object[]{id});
        }
    }
}
