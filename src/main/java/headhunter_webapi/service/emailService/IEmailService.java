package headhunter_webapi.service.emailService;

import headhunter_webapi.dto.authDto.SendEmailToUserDto;
import headhunter_webapi.entity.ServiceResponse;

public interface IEmailService {
    ServiceResponse<String> send(SendEmailToUserDto recipientEmail) throws Exception;
    ServiceResponse<String> verify(Long id, String message);
    String generateSecretCode();
}
