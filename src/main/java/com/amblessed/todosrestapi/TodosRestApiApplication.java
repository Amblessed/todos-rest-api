package com.amblessed.todosrestapi;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.amblessed.todosrestapi.repository")
@EntityScan(basePackages = "com.amblessed.todosrestapi.entity")
public class TodosRestApiApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
        System.setProperty("MYSQL_DOCKER_ROOT_PASSWORD", dotenv.get("MYSQL_DOCKER_ROOT_PASSWORD"));
        SpringApplication.run(TodosRestApiApplication.class, args);
    }

}
