package by.vladyka.module.mail.internal;

import by.vladyka.module.mail.api.EmailNotificationService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailNotificationServiceImpl implements EmailNotificationService {

    private final MailClient mailClient;

    @Override
    public void sendEmail(String to, String subject, String text) {
        // send an email
    }
}
