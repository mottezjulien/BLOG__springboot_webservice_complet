package fr.lapausedev.springboot2;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerDocumentationConfig {

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(paths())
                .build()
                .apiInfo(apiInfo());
    }

    private Predicate<RequestHandler> paths() {
        return Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("La Pause Dev API")
                .description("Ze Best API of the World")
                .version("1.0")
                .build();
    }

}
