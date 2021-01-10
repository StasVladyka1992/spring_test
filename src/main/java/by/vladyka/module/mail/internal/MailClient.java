package by.vladyka.module.mail.internal;

import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
public class MailClient {
    private final boolean connectToMailServer;

    void sendMail() {

    }

    /**
     * There are many cases where a Spring application connects to external resources during startup, for example when
     * initializing a connection pool to a database or some other service. This method just simulates a failing connection
     * during startup. This forces us not to load this bean during integration tests.
     */
    @PostConstruct
    public void connectToMailServer() {
        if (connectToMailServer) {
            throw new RuntimeException("Could not connect to mail server!");
        }
    }

}

