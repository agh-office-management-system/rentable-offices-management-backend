package pl.edu.agh.rentableoffices.office.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.office.dao.OfficeRepository;
import pl.edu.agh.rentableoffices.office.dto.OfficeDto;
import pl.edu.agh.rentableoffices.office.mapper.OfficeMapper;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OfficeDetailsService {
    private final OfficeRepository repository;
    private final OfficeMapper mapper;

    public OfficeDto get(@NotNull Long id) {
        return mapper.toDto(repository.getOne(id));
    }

    public List<OfficeDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }
}
