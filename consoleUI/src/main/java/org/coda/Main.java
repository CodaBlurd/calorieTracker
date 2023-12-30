package org.coda;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main class for the consoleUI module.
 * @SpringBootApplication is a convenience annotation that adds all of the following:
 * @Configuration: Tags the class as a source of bean definitions for the application context.
 * @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
 * @EnableWebMvc: Activates Spring MVC for the application.
 * @ComponentScan: Tells Spring to look for other components, configurations, and services in the org.coda package, letting it find the controllers.
 * @EnableTransactionManagement: Enables Spring's annotation-driven transaction management capability.
 * @EnableJpaRepositories: Enables Spring Data JPA repositories.
 * @Slf4j: Lombok annotation to generate a logger field.
 */

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"org.coda.core, org.coda.core.service, org.coda.core.repository"})
@EnableJpaRepositories(basePackages = {"org.coda.core.repository"})
@Slf4j
public class Main {
    public static void main(String[] args) {

    }
}