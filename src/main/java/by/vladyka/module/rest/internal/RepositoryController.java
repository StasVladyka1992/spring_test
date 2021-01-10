package by.vladyka.module.rest.internal;

import by.vladyka.module.github.api.GitHubMutations;
import by.vladyka.module.github.api.GitHubQueries;
import by.vladyka.module.github.api.GitHubRepository;
import by.vladyka.module.mail.api.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class RepositoryController {

    private final GitHubMutations gitHubMutations;
    private final GitHubQueries gitHubQueries;
    private final EmailNotificationService emailNotificationService;

    @PostMapping("/github/repository")
    public ResponseEntity<Void> createGitHubRepository(@RequestParam("token") String token,
                                                       @RequestParam("repositoryName") String repoName,
                                                       @RequestParam("organizationName") String orgName) {

        if (gitHubQueries.repositoryExists(token, repoName, orgName)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        String repoUrl = gitHubMutations.createRepository(token, new GitHubRepository(repoName, orgName));
        emailNotificationService.sendEmail("user@mail.com", "Your new repository", "Here's your new repository: " + repoUrl);

        return ResponseEntity.ok().build();
    }
}
