package pl.edu.agh.rentableoffices.office.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.office.dao.OfficeRepository;
import pl.edu.agh.rentableoffices.office.dto.CreateOfficeCommand;
import pl.edu.agh.rentableoffices.office.exception.AddressAlreadyExistsException;
import pl.edu.agh.rentableoffices.office.model.Office;
import pl.edu.agh.rentableoffices.common.Address;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OfficeCreateService {
    private OfficeRepository repository;

    public Long create(@NotNull CreateOfficeCommand command) throws AddressAlreadyExistsException {
        Address address = Address.fromDto(command.getAddress());
        if(repository.existsByAddress(address)) {
            throw new AddressAlreadyExistsException(address);
        }
        Office office = Office.create(command.getFloor(), command.getRoomCount(), command.getArea(),
                command.getMaxTenants(), address);
        Long id = repository.save(office).getId();
        log.info("Created apartment. Address = {}", office.getAddress());
        return id;
    }
}
