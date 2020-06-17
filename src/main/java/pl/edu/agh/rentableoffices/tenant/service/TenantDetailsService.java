package pl.edu.agh.rentableoffices.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.tenant.dto.TenantDto;
import pl.edu.agh.rentableoffices.tenant.dao.TenantRepository;
import pl.edu.agh.rentableoffices.tenant.exception.TenantNotFoundException;
import pl.edu.agh.rentableoffices.tenant.mapper.TenantMapper;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TenantDetailsService {
    private final TenantRepository repository;
    private final TenantMapper mapper;

    public TenantDto get(@NotNull Long id) throws TenantNotFoundException {
        return mapper.toDto(repository.get(id));
    }

    public List<TenantDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }
}
