package fr.lapausedev.springboot2;

import com.google.common.base.Predicates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.*;

@SpringBootApplication
@EnableSwagger2 //http://localhost:8080/swagger-ui.html
public class SpringApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringApplicationRunner.class, args);
	}

}
