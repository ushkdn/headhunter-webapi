package headhunter_webapi.service.emailService;

import headhunter_webapi.entity.ServiceResponse;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Enumeration;
import java.util.Random;
import java.util.ServiceConfigurationError;

@Service
public class EmailService implements IEmailService{

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public ServiceResponse<String> sendEmail(String recipientEmail, String subject, String message) {
        var serviceResponse = new ServiceResponse<String>();
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(recipientEmail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message);
            javaMailSender.send(mimeMessage);
            serviceResponse.data = null;
            serviceResponse.success = true;
            serviceResponse.message = String.format("Security code for %s successfully sent to your email.", subject);
        } catch (Exception ex) {
            log.error("Error sending email: {}", ex.getMessage(), ex);
            serviceResponse.data = null;
            serviceResponse.success = false;
            serviceResponse.message = ex.getMessage();
        }
        return serviceResponse;
    }


    @Override
    public ServiceResponse<String> verifyEmail(Long id, String message) {
        return null;
    }

    @Override
    @Async
    public ServiceResponse<String> resendEmail(Long id, String message) {
        return null;
    }
    @Override
    public String generateSecretCode(){
        final String CHARACTERS="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final int LENGTH=10;
        var random = new Random();
        var sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }
}
