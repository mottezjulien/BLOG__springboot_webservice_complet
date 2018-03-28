package fr.lapausedev.springboot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 //http://localhost:8080/swagger-ui.html
public class SpringApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationRunner.class, args);
    }

}
