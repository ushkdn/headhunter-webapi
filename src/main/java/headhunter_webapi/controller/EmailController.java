package headhunter_webapi.controller;

import headhunter_webapi.service.emailService.IEmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/email")
@Tag(name = "TestController")
public class EmailController {

    private final IEmailService _emailService;

    public EmailController(IEmailService emailService){
        _emailService=emailService;
    }
}
