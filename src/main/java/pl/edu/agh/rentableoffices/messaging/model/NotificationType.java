package pl.edu.agh.rentableoffices.messaging.model;

public enum NotificationType {
    MESSAGE_SENT,
    MESSAGE_READ,

    TENANT_CREATED,
    TENANT_VERIFIED,
    TENANT_REJECTED,
    TENANT_ASSIGNED,
    TENANT_REMOVED,

    OFFICE_UPDATED,

    SURVEY_CREATED,
    SURVEY_ANSWER_SUBMITTED,
    SURVEY_ANSWER_REJECTED;
}
