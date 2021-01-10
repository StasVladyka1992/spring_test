package by.vladyka.module.rest.internal;

import by.vladyka.module.github.GitHubModuleMock;
import by.vladyka.module.mail.EmailModuleMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@TestPropertySource(properties = "mail.enabled=false")
@Import({GitHubModuleMock.class, EmailModuleMock.class})
public class RepositoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmailModuleMock emailModuleMock;

    @Autowired
    private GitHubModuleMock githubModuleMock;

    @Test
    void givenRepositoryDoesNotExist_thenRepositoryIsCreatedSuccessfully() throws Exception {

        String repositoryUrl = "https://github.com/reflectoring/reflectoring.github.io";

        githubModuleMock.givenDefaultState(repositoryUrl);
        emailModuleMock.givenSendMailSucceeds();

        mockMvc.perform(post("/github/repository")
                .param("token", "123")
                .param("repositoryName", "foo")
                .param("organizationName", "bar"))
                .andExpect(status().is(200));

        emailModuleMock.assertSentMailContains(repositoryUrl);
        githubModuleMock.assertRepositoryCreated();
    }

    @Test
    void givenRepositoryExists_thenReturnsBadRequest() throws Exception {

        String repositoryUrl = "https://github.com/reflectoring/reflectoring.github.io";

        githubModuleMock.givenDefaultState(repositoryUrl);
        githubModuleMock.givenRepositoryExists();
        emailModuleMock.givenSendMailSucceeds();

        mockMvc.perform(post("/github/repository")
                .param("token", "123")
                .param("repositoryName", "foo")
                .param("organizationName", "bar"))
                .andExpect(status().is(400));

        emailModuleMock.assertNoMailSent();
        githubModuleMock.assertRepositoryNotCreated();
    }
}

