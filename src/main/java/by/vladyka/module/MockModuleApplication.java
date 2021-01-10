package by.vladyka.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan() //TODO - обязательно ли???
public class MockModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockModuleApplication.class, args);
    }

}
