package pl.edu.agh.rentableoffices.messaging.mapper;

import org.springframework.stereotype.Component;
import pl.edu.agh.rentableoffices.common.AbstractMapper;
import pl.edu.agh.rentableoffices.messaging.dto.MessageDto;
import pl.edu.agh.rentableoffices.messaging.model.Message;

@Component
public class MessageMapper implements AbstractMapper<Message, MessageDto> {
    @Override
    public MessageDto toDto(Message entity) {
        return new MessageDto(entity.getFrom(), entity.getTo(), entity.getContent(), entity.getCreatedAt());
    }
}
