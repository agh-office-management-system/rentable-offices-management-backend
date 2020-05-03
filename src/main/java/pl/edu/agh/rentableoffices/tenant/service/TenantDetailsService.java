package pl.edu.agh.rentableoffices.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.tenant.dto.TenantDto;
import pl.edu.agh.rentableoffices.tenant.dao.TenantRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
@Transactional
@RequiredArgsConstructor
public class TenantDetailsService {
    private TenantRepository repository;

    public TenantDto get(@NotNull Long id) {
        return null; //TODO
    }
}
