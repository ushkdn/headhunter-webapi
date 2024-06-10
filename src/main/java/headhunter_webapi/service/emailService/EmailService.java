package headhunter_webapi.service.emailService;

import headhunter_webapi.entity.ServiceResponse;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService{
    @Override
    public ServiceResponse<String> sendEmail(String topic, String recipientEmail) {
    }

    @Override
    public ServiceResponse<String> verifyEmail(Long id, String securityCode) {
        return null;
    }

    @Override
    public ServiceResponse<String> resendCode(Long id) {
        return null;
    }

    private String CreateSecurityCode(){

    }
}
