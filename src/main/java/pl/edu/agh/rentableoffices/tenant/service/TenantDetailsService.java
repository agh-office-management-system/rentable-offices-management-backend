package pl.edu.agh.rentableoffices.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.tenant.dto.TenantDto;
import pl.edu.agh.rentableoffices.tenant.dao.TenantRepository;
import pl.edu.agh.rentableoffices.tenant.exception.TenantNotFoundException;
import pl.edu.agh.rentableoffices.tenant.mapper.TenantMapper;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
@Transactional
@RequiredArgsConstructor
public class TenantDetailsService {
    private TenantRepository repository;
    private TenantMapper mapper;

    public TenantDto get(@NotNull Long id) throws TenantNotFoundException {
        return mapper.toDto(repository.get(id));
    }
}
