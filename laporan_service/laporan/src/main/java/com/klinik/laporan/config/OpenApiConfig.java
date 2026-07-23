package com.klinik.laporan.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI laporanOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Laporan Service API")
                        .version("1.0.0")
                        .description("Dokumentasi API untuk layanan Laporan Klinik")
                        .contact(new Contact().name("Tim Klinik").email("support@klinik.local"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
