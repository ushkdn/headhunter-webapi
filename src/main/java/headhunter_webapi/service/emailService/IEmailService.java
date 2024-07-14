package headhunter_webapi.service.emailService;

import headhunter_webapi.entity.ServiceResponse;

public interface IEmailService {
    ServiceResponse<String> sendEmail(String recipientEmail,String subject, String message) throws Exception;
    ServiceResponse<String> verifyEmail(Long id, String message);
    ServiceResponse<String> resendEmail(Long id, String message);
    String generateSecretCode();
}
