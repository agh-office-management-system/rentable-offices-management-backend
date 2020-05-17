package pl.edu.agh.rentableoffices.common.queue;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

@RequiredArgsConstructor
public class AbstractSender<T extends Serializable> {
    @Autowired
    private RabbitTemplate template;

    @Value("${offices.exchange.name}")
    private String exchange;

    // Set this value in subclass
    @Setter(value = AccessLevel.PROTECTED)
    private String routingKey;

    public void send(T data) {
        MessageBase<T> message = createData(data);
        template.convertAndSend(exchange, routingKey, message);
    }

    protected MessageBase<T> createData(T dto) {
        return MessageBase.create(dto);
    }
}
