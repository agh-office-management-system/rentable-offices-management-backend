package pl.edu.agh.rentableoffices.messaging.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.messaging.dao.MessageRepository;
import pl.edu.agh.rentableoffices.messaging.dto.CreateGroupMessageCommand;
import pl.edu.agh.rentableoffices.messaging.dto.CreateMessageCommand;
import pl.edu.agh.rentableoffices.messaging.dto.MessageDto;
import pl.edu.agh.rentableoffices.messaging.exception.MessageNotFound;
import pl.edu.agh.rentableoffices.messaging.exception.ReceiverNotFound;
import pl.edu.agh.rentableoffices.messaging.mapper.MessageMapper;
import pl.edu.agh.rentableoffices.messaging.model.Message;
import pl.edu.agh.rentableoffices.messaging.model.NotificationType;
import pl.edu.agh.rentableoffices.user.UserService;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MessagingService {
    private final MessageRepository repository;
    private final MessageMapper mapper;
    private final NotificationService notificationService;
    private final UserService userService;

    public void sendMessage(@NotNull CreateMessageCommand command) throws ReceiverNotFound {
        if(!userService.userExists(command.getTo())) {
            throw new ReceiverNotFound(command.getTo());
        } else {
            Message message = Message.create(command.getFrom(), command.getTo(), command.getContent());
            Long id = repository.save(message).getId();
            notificationService.sendNotification(command.getFrom(), command.getTo(), NotificationType.MESSAGE_SENT, new Object[]{command.getFrom(), id});
        }
    }

    public void markAsRead(@NotNull Long id) throws MessageNotFound {
        Message message = repository.get(id);
        message.markAsRead();
        this.notificationService.sendNotification(message.getTo(), message.getFrom(), NotificationType.MESSAGE_READ, new Object[]{id});
    }

    public MessageDto getMessage(@NotNull Long id) throws MessageNotFound {
        return mapper.toDto(repository.get(id));
    }
}
