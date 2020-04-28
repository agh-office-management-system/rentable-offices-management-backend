package pl.edu.agh.rentableoffices.apartment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.apartment.dao.ApartmentRepository;
import pl.edu.agh.rentableoffices.apartment.dto.ApartmentDto;
import pl.edu.agh.rentableoffices.apartment.mapper.ApartmentMapper;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
@Transactional
@RequiredArgsConstructor
public class ApartmentDetailsService {
    private final ApartmentRepository repository;
    private final ApartmentMapper mapper;

    public ApartmentDto get(@NotNull Long id) {
        return mapper.toDto(repository.getOne(id));
    }
}
