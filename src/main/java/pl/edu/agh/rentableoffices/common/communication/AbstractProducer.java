package pl.edu.agh.rentableoffices.common.communication;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractProducer<T> {
    @Autowired
    private RabbitTemplate queueSender;

    public abstract String queueName();
/*
    public void send(AbstractMessage<T> message) {
        Message mes = new Message(message);

        queueSender.send(queueName(), message);
    }*/
}
