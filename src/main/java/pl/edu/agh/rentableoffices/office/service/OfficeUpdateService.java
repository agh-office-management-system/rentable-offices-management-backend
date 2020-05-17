package pl.edu.agh.rentableoffices.office.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.messaging.service.NotificationCreateService;
import pl.edu.agh.rentableoffices.office.dao.OfficeRepository;
import pl.edu.agh.rentableoffices.office.dto.UpdateOfficeCommand;
import pl.edu.agh.rentableoffices.office.exception.MaxOfficeCapacityReachedException;
import pl.edu.agh.rentableoffices.office.exception.OfficeNotFoundException;
import pl.edu.agh.rentableoffices.office.model.Office;
import pl.edu.agh.rentableoffices.tenant.dao.TenantRepository;
import pl.edu.agh.rentableoffices.tenant.exception.TenantNotFoundException;
import pl.edu.agh.rentableoffices.tenant.model.Tenant;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OfficeUpdateService {
    private final OfficeRepository repository;
    private final TenantRepository tenantRepository;
    private final NotificationCreateService notificationCreateService;

    public void update(@NotNull Long id, @NotNull UpdateOfficeCommand command) throws OfficeNotFoundException {
        Office office = repository.get(id);
        office.update(command.getNewRoomCount(), command.getNewArea(), command.getNewMaxTenants(), command.getNewAddress());
        log.info("Office {} updated. Room count = {}, area = {}, max tenants = {},address = {}",
                command.getNewRoomCount(), command.getNewArea(), command.getNewMaxTenants(), command.getNewAddress());
        if(!office.getTenants().isEmpty()) {
            notificationCreateService.createOfficeUpdatedNotification(office.getTenants().stream().map(Tenant::getEmail).collect(Collectors.toList()), id);
        }
    }

    public void assignTenant(@NotNull Long id, @NotNull Long tenantId)
            throws OfficeNotFoundException, TenantNotFoundException, MaxOfficeCapacityReachedException {
        Office office = repository.get(id);
        Tenant tenant = tenantRepository.get(tenantId);
        office.assignTenant(tenant);
        log.info("Tenant {} assigned to the office {}", tenant.getEmail(), office.getAddress());
        notificationCreateService.createTenantAssignedNotification(tenant.getEmail(), id);
    }

    public void removeTenant(@NotNull Long id, @NotNull Long tenantId) throws OfficeNotFoundException, TenantNotFoundException {
        Office office = repository.get(id);
        Tenant tenant = tenantRepository.get(tenantId);
        office.removeTenant(tenant);
        log.info("Tenant {} removed from the office {}", tenant.getEmail(), office.getAddress());
        notificationCreateService.createTenantRemovedNotification(tenant.getEmail(), id);
    }
}
