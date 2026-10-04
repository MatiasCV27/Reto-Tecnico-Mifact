package com.matias.Reto_Tecnico_Mifact.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Product Api",
                description = "This is a technical challenge for MiFact",
                version = "1.0.0",
                contact = @Contact(
                        name = "Matias", email = "matiascriollo57@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0", url = "https://www.apache.org/license/LICENSE-2.0"
                )
        ),
        servers = @Server(
                url = "http://localhost:1100",
                description = "Local test"
        )
)
@Configuration
public class OpenAPIConfig {
}
