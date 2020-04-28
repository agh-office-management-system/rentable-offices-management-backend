package pl.edu.agh.rentableoffices.apartment.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.apartment.dao.ApartmentRepository;
import pl.edu.agh.rentableoffices.apartment.dto.CreateApartmentCommand;
import pl.edu.agh.rentableoffices.apartment.model.Apartment;
import pl.edu.agh.rentableoffices.common.Address;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CreateApartmentService {
    private ApartmentRepository repository;

    public Long create(CreateApartmentCommand request) {
        //TODO validation
        Apartment apartment = Apartment.create(request.getFloor(), request.getRoomCount(), request.getArea(), Address.fromDto(request.getAddress()));
        Long id = repository.save(apartment).getId();
        log.info("Created apartment. Address = {}", apartment.getAddress());
        return id;
    }
}
