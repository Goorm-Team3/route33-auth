package io.goorm.rout33.route33auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@ConfigurationPropertiesScan
@SpringBootApplication
public class Route33AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(Route33AuthApplication.class, args);
    }

}
