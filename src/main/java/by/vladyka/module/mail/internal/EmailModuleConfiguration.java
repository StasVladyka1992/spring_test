package by.vladyka.module.mail.internal;

import by.vladyka.module.mail.api.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(EmailModuleConfigurationProperties.class)
@RequiredArgsConstructor
public class EmailModuleConfiguration {
    private final EmailModuleConfigurationProperties configurationProperties;

    @Bean
    public EmailNotificationService emailNotificationService(MailClient mailClient) {
        return new EmailNotificationServiceImpl(mailClient);
    }

    @Bean
    public MailClient mailClient() {
        return new MailClient(configurationProperties.isEnabled());
    }
}
