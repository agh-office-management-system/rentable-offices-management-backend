package pl.edu.agh.rentableoffices.office.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.office.dao.OfficeRepository;
import pl.edu.agh.rentableoffices.office.dto.CreateOfficeCommand;
import pl.edu.agh.rentableoffices.office.model.Office;
import pl.edu.agh.rentableoffices.common.Address;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OfficeCreateService {
    private OfficeRepository repository;

    public Long create(CreateOfficeCommand request) {
        //TODO validation
        Office office = Office.create(request.getFloor(), request.getRoomCount(), request.getArea(), Address.fromDto(request.getAddress()));
        Long id = repository.save(office).getId();
        log.info("Created apartment. Address = {}", office.getAddress());
        return id;
    }
}
