package pl.edu.agh.rentableoffices.messaging.queue.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.common.queue.AbstractSender;

@Slf4j
@Service
public class UserMessageSender extends AbstractSender<UserMessageDto> {

    @Override
    @Value("${message.routingKey.name}")
    protected void setRoutingKey(String routingKey) {
        super.setRoutingKey(routingKey);
    }

}
