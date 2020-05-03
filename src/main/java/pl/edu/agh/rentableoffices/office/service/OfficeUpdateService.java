package pl.edu.agh.rentableoffices.office.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.office.dao.OfficeRepository;
import pl.edu.agh.rentableoffices.office.dto.UpdateOfficeCommand;
import pl.edu.agh.rentableoffices.office.model.Office;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

//TODO notify tenant
@Service
@Transactional
@RequiredArgsConstructor
public class OfficeUpdateService {
    private final OfficeRepository repository;

    public void update(@NotNull Long id, UpdateOfficeCommand command) {
        Office office = repository.getOne(id);
        office.update(command.getNewRoomCount(), command.getNewArea(), command.getNewAddress());
    }

    //TODO - Jeżeli wszystkie warunki są spełnione to informacja trafia do najemcy oraz pracownika administracji
    //TODO - Jeżeli przypisanie nie może zostać zrealizowane informacja trafia tylko do pracownika administracji
    public void assignTenant(@NotNull Long id, @NotNull Long tenantId) {

    }
}
