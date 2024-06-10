package headhunter_webapi.service.emailService;

import headhunter_webapi.entity.ServiceResponse;

public interface IEmailService {
    ServiceResponse<String> sendEmail(String topic, String recipientEmail);
    ServiceResponse<String> verifyEmail(Long id, String securityCode);
    ServiceResponse<String> resendCode(Long id);
}
