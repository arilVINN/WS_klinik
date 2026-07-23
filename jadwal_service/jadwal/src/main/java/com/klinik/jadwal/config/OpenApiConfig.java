package com.klinik.jadwal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("Jadwal Service API")
                                                .version("v1")
                                                .description("Dokumentasi API untuk layanan jadwal klinik")
                                                .contact(new Contact()
                                                                .name("Tim Klinik")
                                                                .email("support@klinik.local")));
        }
}
