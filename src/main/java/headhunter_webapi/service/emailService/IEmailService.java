package headhunter_webapi.service.emailService;

import headhunter_webapi.entity.ServiceResponse;

public interface IEmailService {
    ServiceResponse<String> send(String recipientEmail) throws Exception;
    ServiceResponse<String> verify(Long id, String message);
    String generateSecretCode();
}
