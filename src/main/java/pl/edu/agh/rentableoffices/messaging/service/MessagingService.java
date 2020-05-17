package pl.edu.agh.rentableoffices.messaging.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.messaging.dao.MessageRepository;
import pl.edu.agh.rentableoffices.messaging.dto.CreateMessageCommand;
import pl.edu.agh.rentableoffices.messaging.dto.MessageDto;
import pl.edu.agh.rentableoffices.messaging.exception.MessageNotFound;
import pl.edu.agh.rentableoffices.messaging.exception.ReceiverNotFound;
import pl.edu.agh.rentableoffices.messaging.mapper.MessageMapper;
import pl.edu.agh.rentableoffices.messaging.model.UserMessage;
import pl.edu.agh.rentableoffices.messaging.model.NotificationType;
import pl.edu.agh.rentableoffices.messaging.queue.UserMessageDto;
import pl.edu.agh.rentableoffices.messaging.queue.UserMessageSender;
import pl.edu.agh.rentableoffices.user.UserService;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessagingService {
    private final MessageRepository repository;
    private final MessageMapper mapper;
    private final NotificationService notificationService;
    private final UserService userService;
    private final UserMessageSender sender;

    public void sendMessage(@NotNull CreateMessageCommand command) throws ReceiverNotFound {
        if(!userService.userExists(command.getTo())) {
            throw new ReceiverNotFound(command.getTo());
        } else {
            UserMessageDto userMessage = UserMessageDto.builder()
                    .content(command.getContent())
                    .from(command.getFrom())
                    .to(command.getTo())
                    .build();

            log.info("Sending message to user \"{}\" from \"{}\"", userMessage.getTo(), userMessage.getFrom());
            sender.send(userMessage);
        }
    }

    public void markAsRead(@NotNull Long id) throws MessageNotFound {
       /* UserMessage userMessage = repository.get(id);
        userMessage.markAsRead();
        this.notificationService.sendNotification(userMessage.getTo(), userMessage.getFrom(), NotificationType.MESSAGE_READ, new Object[]{id});*/
    }

    public MessageDto getMessage(@NotNull Long id) throws MessageNotFound {
        return mapper.toDto(repository.get(id));
    }
}
