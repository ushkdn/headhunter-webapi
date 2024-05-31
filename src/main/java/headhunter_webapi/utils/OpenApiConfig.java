package headhunter_webapi.utils;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name= "Daniil",
                        email = "ushkandn@gmail.com",
                        url = "https://github.com/ushkdn"
                ),
                description = "Web-application for searching and hiring employees",
                title = "headhunter",
                version = "1.0"
        )
//Enable security for all endpoints        ,
//        security = {
//                @SecurityRequirement(
//                        name="bearerAuth"
//                )
//        }
)
@SecurityScheme(
        name="Bearer token",
        description = "JWT auth",
        scheme = "bearer",
        type= SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
