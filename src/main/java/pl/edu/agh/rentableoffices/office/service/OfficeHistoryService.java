package pl.edu.agh.rentableoffices.office.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.office.dao.OfficeRepository;
import pl.edu.agh.rentableoffices.office.dto.OfficeHistoryDto;
import pl.edu.agh.rentableoffices.office.exception.OfficeNotFoundException;
import pl.edu.agh.rentableoffices.office.mapper.OfficeHistoryMapper;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OfficeHistoryService {
    private OfficeHistoryMapper mapper;
    private OfficeRepository repository;

    public List<OfficeHistoryDto>  getOfficeHistory(Long officeId) throws OfficeNotFoundException {
        return mapper.toDtoList(repository.get(officeId).getHistory());
    }
}
