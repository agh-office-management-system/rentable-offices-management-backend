package pl.edu.agh.rentableoffices.messaging.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.rentableoffices.messaging.exception.MessageNotFound;
import pl.edu.agh.rentableoffices.messaging.model.UserMessage;

import java.util.List;

public interface MessageRepository extends JpaRepository<UserMessage, Long> {
    List<UserMessage> findAllByToEquals(String to);

    default UserMessage get(Long id) throws MessageNotFound {
        return findById(id).orElseThrow(() -> new MessageNotFound(id));
    }
}
