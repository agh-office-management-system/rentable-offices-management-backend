package pl.edu.agh.rentableoffices.messaging.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.agh.rentableoffices.messaging.model.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("select n from Notification n where n.to = :user order by n.id desc")
    List<Notification> getUserNotifications(String user);
}
