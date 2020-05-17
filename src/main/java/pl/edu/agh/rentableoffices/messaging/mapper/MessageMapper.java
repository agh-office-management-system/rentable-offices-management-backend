package pl.edu.agh.rentableoffices.messaging.mapper;

import org.springframework.stereotype.Component;
import pl.edu.agh.rentableoffices.common.AbstractMapper;
import pl.edu.agh.rentableoffices.messaging.dto.MessageDto;
import pl.edu.agh.rentableoffices.messaging.model.UserMessage;

@Component
public class MessageMapper implements AbstractMapper<UserMessage, MessageDto> {
    @Override
    public MessageDto toDto(UserMessage entity) {
        return new MessageDto(entity.getId(), entity.getFrom(), entity.getTo(), entity.getContent(), entity.isRead(), entity.getCreatedAt());
    }
}
