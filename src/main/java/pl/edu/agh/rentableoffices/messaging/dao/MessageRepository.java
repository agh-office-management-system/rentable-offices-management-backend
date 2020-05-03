package pl.edu.agh.rentableoffices.messaging.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.rentableoffices.messaging.exception.MessageNotFound;
import pl.edu.agh.rentableoffices.messaging.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByToEquals(String to);

    default Message get(Long id) throws MessageNotFound {
        return findById(id).orElseThrow(() -> new MessageNotFound(id));
    }
}
