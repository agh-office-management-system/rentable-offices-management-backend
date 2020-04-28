package pl.edu.agh.rentableoffices.apartment.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.apartment.dao.ApartmentRepository;
import pl.edu.agh.rentableoffices.apartment.dto.ApartmentDto;
import pl.edu.agh.rentableoffices.apartment.mapper.ApartmentMapper;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ApartmentDetailsService {
    private ApartmentRepository repository;
    private ApartmentMapper mapper;

    public ApartmentDto get(@NotNull Long id) {
        return mapper.toDto(repository.getOne(id));
    }
}
