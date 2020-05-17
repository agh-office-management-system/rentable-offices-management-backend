package pl.edu.agh.rentableoffices.messaging.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.rentableoffices.messaging.dao.NotificationRepository;
import pl.edu.agh.rentableoffices.messaging.dto.NotificationDto;
import pl.edu.agh.rentableoffices.messaging.mapper.NotificationMapper;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotificationDetailsService {
    private final NotificationRepository repository;
    private final NotificationMapper mapper;

    public List<NotificationDto> getNotifications(String username) {
        return mapper.toDtoList(repository.getUserNotifications(username));
    }
}
