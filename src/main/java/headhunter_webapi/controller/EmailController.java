package headhunter_webapi.controller;

import headhunter_webapi.entity.ServiceResponse;
import headhunter_webapi.service.emailService.IEmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/email")
@Tag(name = "TestController")
public class EmailController {

    private final IEmailService _emailService;

    public EmailController(IEmailService emailService){
        _emailService=emailService;
    }

    @PostMapping("/verify/{id}")
    public ServiceResponse<String> verify(@PathVariable Long id, @RequestBody String secretCode){
        return _emailService.verify(id, secretCode);
    }

    @PostMapping("/send")
    public ServiceResponse<String> send(@RequestBody String recipientEMail) throws Exception {
        return _emailService.send(recipientEMail);
    }

}
