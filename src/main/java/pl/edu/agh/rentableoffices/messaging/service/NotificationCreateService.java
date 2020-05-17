package pl.edu.agh.rentableoffices.messaging.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.messaging.model.NotificationType;
import pl.edu.agh.rentableoffices.messaging.queue.notification.NotificationMessageDto;
import pl.edu.agh.rentableoffices.messaging.queue.notification.NotificationSender;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NotificationCreateService {
    //TODO change after login mechanism introduced
    private static final String SYSTEM_USER = "SYSTEM";
    private static final String ADMINISTRATION_EMPLOYEE = "ADMINISTRATION";

    private final NotificationSender sender;

    public void createMessageNotification(String from, String to, Long messageId) {
        log.info("Sending message sent notification to {} from {}", to, from);

        NotificationMessageDto message = NotificationMessageDto.builder()
                .from(from)
                .receiver(to)
                .notificationType(NotificationType.MESSAGE_SENT)
                .payload(new Object[]{messageId})
                .build();

        sender.send(message);
    }

    public void createMessageReadNotification(String to, Long messageId) {
        log.info("Sending message read notification to {}", to);
        NotificationMessageDto message = NotificationMessageDto.builder()
                .from(SYSTEM_USER)
                .receiver(to)
                .notificationType(NotificationType.MESSAGE_READ)
                .payload(new Object[]{messageId})
                .build();

        sender.send(message);
    }

    public void createSurveyCreatedNotification(List<String> tenantEmails, Long surveyId) {
        log.info("Sending survey created notification to tenants {}", tenantEmails);
        NotificationMessageDto message = NotificationMessageDto.builder()
                .from(SYSTEM_USER)
                .receivers(tenantEmails)
                .notificationType(NotificationType.SURVEY_CREATED)
                .payload(new Object[]{surveyId})
                .build();

        sender.send(message);
    }

    public void createSurveyAnswerSubmittedNotification(String tenantEmail, Long answerId) {
        log.info("Sending survey answer submitted notification to administration employee");
        NotificationMessageDto message = NotificationMessageDto.builder()
                .from(SYSTEM_USER)
                .receiver(ADMINISTRATION_EMPLOYEE)
                .notificationType(NotificationType.SURVEY_ANSWER_SUBMITTED)
                .payload(new Object[]{tenantEmail, answerId})
                .build();

        sender.send(message);
    }

    public void createSurveyAnswerRejectedNotification(String tenantEmail) {
        log.info("Sending survey answer rejected notification to administration employee");
        NotificationMessageDto message = NotificationMessageDto.builder()
                .from(SYSTEM_USER)
                .receiver(ADMINISTRATION_EMPLOYEE)
                .notificationType(NotificationType.SURVEY_ANSWER_REJECTED)
                .payload(new Object[]{tenantEmail})
                .build();

        sender.send(message);
    }

    public void createTenantCreatedNotification(String tenantEmail) {
        log.info("Sending tenant created notification to administration employee");
        NotificationMessageDto message = NotificationMessageDto.builder()
                .from(SYSTEM_USER)
                .receiver(ADMINISTRATION_EMPLOYEE)
                .notificationType(NotificationType.TENANT_CREATED)
                .payload(new Object[]{tenantEmail})
                .build();

        sender.send(message);
    }

    public void createTenantVerifiedNotification(String tenantEmail) {
        log.info("Sending tenant verified notification to administration employee");
        NotificationMessageDto message = NotificationMessageDto.builder()
                .from(SYSTEM_USER)
                .receiver(ADMINISTRATION_EMPLOYEE)
                .notificationType(NotificationType.TENANT_VERIFIED)
                .payload(new Object[]{tenantEmail})
                .build();

        sender.send(message);
    }

    public void createTenantRejectedNotification(String tenantEmail, String rejectionReason) {
        log.info("Sending tenant verification rejected notification to administration employee");
        NotificationMessageDto message = NotificationMessageDto.builder()
                .from(SYSTEM_USER)
                .receiver(ADMINISTRATION_EMPLOYEE)
                .notificationType(NotificationType.TENANT_REJECTED)
                .payload(new Object[]{tenantEmail, rejectionReason})
                .build();

        sender.send(message);
    }

    public void createOfficeUpdatedNotification(List<String> tenantEmails, Long officeId) {
        log.info("Sending office notification to tenants {}", tenantEmails);
        NotificationMessageDto message = NotificationMessageDto.builder()
                .from(SYSTEM_USER)
                .receivers(tenantEmails)
                .notificationType(NotificationType.OFFICE_UPDATED)
                .payload(new Object[]{officeId})
                .build();

        sender.send(message);
    }

    public void createTenantAssignedNotification(String tenantEmail, Long officeId) {
        log.info("Sending tenant assigned notification to tenant {}", tenantEmail);
        NotificationMessageDto message = NotificationMessageDto.builder()
                .from(SYSTEM_USER)
                .receiver(tenantEmail)
                .notificationType(NotificationType.TENANT_ASSIGNED)
                .payload(new Object[]{officeId})
                .build();

        sender.send(message);
    }

    public void createTenantRemovedNotification(String tenantEmail, Long officeId) {
        log.info("Sending tenant assigned notification to tenant {}", tenantEmail);
        NotificationMessageDto message = NotificationMessageDto.builder()
                .from(SYSTEM_USER)
                .receiver(tenantEmail)
                .notificationType(NotificationType.TENANT_REMOVED)
                .payload(new Object[]{officeId})
                .build();

        sender.send(message);
    }

}
