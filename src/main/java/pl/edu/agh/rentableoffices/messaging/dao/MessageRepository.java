package pl.edu.agh.rentableoffices.messaging.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.edu.agh.rentableoffices.messaging.exception.MessageNotFound;
import pl.edu.agh.rentableoffices.messaging.model.UserMessage;

import java.util.List;

public interface MessageRepository extends JpaRepository<UserMessage, Long> {
    @Query("select u from UserMessage u where u.to = :user order by u.id desc")
    List<UserMessage> getUserMessages(@Param("user") String user);

    default UserMessage get(Long id) throws MessageNotFound {
        return findById(id).orElseThrow(() -> new MessageNotFound(id));
    }
}
