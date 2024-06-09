package headhunter_webapi.controller;

import headhunter_webapi.entity.ServiceResponse;
import headhunter_webapi.service.emailService.IEmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/verify-email/{id}")
    public ServiceResponse<String> verifyEmail(@PathVariable Long id, String securityCode){
        return _emailService.verifyEmail(id, securityCode);
    }
    @PostMapping("/resend-code/{id}")
    public ServiceResponse<String> resendCode(@PathVariable Long id){
        return _emailService.resendCode(id);
    }
}
