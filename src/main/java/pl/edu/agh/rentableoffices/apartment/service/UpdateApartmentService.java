package pl.edu.agh.rentableoffices.apartment.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.apartment.dao.ApartmentRepository;
import pl.edu.agh.rentableoffices.apartment.dto.UpdateApartmentCommand;
import pl.edu.agh.rentableoffices.apartment.model.Apartment;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

//TODO notify tenant
@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateApartmentService {
    private ApartmentRepository repository;

    public void update(@NotNull Long id, UpdateApartmentCommand command) {
        Apartment apartment = repository.getOne(id);
        apartment.update(command.getNewRoomCount(), command.getNewArea(), command.getNewAddress());
    }
}
