package org.coda.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.coda.core, org.coda.core.repository, " +
        "org.coda.core.service, org.coda.core.entities"})
public class AppConfig{



}
