package pl.edu.agh.rentableoffices.tenant.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.messaging.dto.CreateGroupMessageCommand;
import pl.edu.agh.rentableoffices.messaging.service.MessagingService;
import pl.edu.agh.rentableoffices.tenant.dao.TenantRepository;
import pl.edu.agh.rentableoffices.tenant.dto.CreateMessageForTenantsCommand;
import pl.edu.agh.rentableoffices.tenant.model.Tenant;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TenantMessageService {
    private final TenantRepository repository;

    public void createMessageForTenants(CreateMessageForTenantsCommand command) {
        List<Tenant> tenants = repository.findAllById(command.getTenantIds());
        tenants.forEach(tenant -> {
            //TODO
            switch (tenant.getPreferredMeansOfCommunication()) {
                case SMS:
                    log.info("Sending sms to {}", tenant.getPhoneNumber());
                    break;
                case EMAIL:
                    log.info("Sending email to {}", tenant.getEmail());
            }
        });
    }
}
