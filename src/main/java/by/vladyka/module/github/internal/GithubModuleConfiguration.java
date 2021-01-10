package by.vladyka.module.github.internal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubModuleConfiguration {

    @Bean
    public GitHubService gitHubService() {
        return new GitHubService();
    }
}

