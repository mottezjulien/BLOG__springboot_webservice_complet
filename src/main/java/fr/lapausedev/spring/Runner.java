package fr.lapausedev.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 //http://localhost:8080/swagger-ui.html
public class Runner {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

}
