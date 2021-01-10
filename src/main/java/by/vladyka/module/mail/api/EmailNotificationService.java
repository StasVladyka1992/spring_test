package by.vladyka.module.mail.api;

public interface EmailNotificationService {
    void sendEmail(String to, String subject, String text);
}
