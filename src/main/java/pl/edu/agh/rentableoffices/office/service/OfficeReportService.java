package pl.edu.agh.rentableoffices.office.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.office.dao.OfficeRepository;
import pl.edu.agh.rentableoffices.office.dto.OfficeReportDto;
import pl.edu.agh.rentableoffices.office.mapper.OfficeReportMapper;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OfficeReportService {
    private final OfficeRepository repository;
    private final OfficeReportMapper mapper;

    public List<OfficeReportDto> createOfficeReport() {
        return mapper.toDtoList(repository.findAll());
    }
}
