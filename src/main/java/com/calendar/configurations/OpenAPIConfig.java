package com.calendar.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI myOpenAPIConfig(){
        Contact developer = new Contact();
        developer.setEmail("taminto.a@gmail.com");
        developer.setName("Antonio");

        Info info = new Info();
        info.title("Calendar");
        info.setContact(developer);
        info.setVersion("1.0");
        info.setDescription("welcome to my webApp of a calendar");

        return new OpenAPI().info(info);
    }
}
