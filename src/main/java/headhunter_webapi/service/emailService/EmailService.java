package headhunter_webapi.service.emailService;

import headhunter_webapi.entity.CacheEntity;
import headhunter_webapi.entity.ServiceResponse;
import headhunter_webapi.repository.CacheRepository;
import headhunter_webapi.repository.UserRepository;
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

import java.time.Duration;
import java.util.Enumeration;
import java.util.Random;
import java.util.ServiceConfigurationError;

@Service
public class EmailService implements IEmailService{

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender _javaMailSender;
    private final UserRepository _userRepository;
    private final CacheRepository _cacheRepository;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailService(JavaMailSender javaMailSender, UserRepository userRepository, CacheRepository cacheRepository) {
        _javaMailSender = javaMailSender;
        _userRepository=userRepository;
        _cacheRepository=cacheRepository;
    }


    @Override
    @Async
    public ServiceResponse<String> send(String recipientEmail) {
        var serviceResponse = new ServiceResponse<String>();
        try {
            var storedUser=_userRepository.findUserByEmail(recipientEmail).orElseThrow(()->new Exception("User with this email not found."));
            if(storedUser.getVerified()){
                throw new Exception("You have already confirmed your email.");
            }
            SimpleMailMessage mailMessage= new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(recipientEmail);
            var message=generateSecretCode();
            var subject="Email verification";
            mailMessage.setSubject(subject);
            mailMessage.setText(message);
            _javaMailSender.send(mailMessage);

            _cacheRepository.save(CacheEntity.SECRET_CODE.toString(), recipientEmail, message, Duration.ofMinutes(3));
            serviceResponse.data = null;
            serviceResponse.success = true;
            serviceResponse.message = String.format("Security code for %s successfully sent to your email.", subject);
        } catch (Exception ex) {
            serviceResponse.data = null;
            serviceResponse.success = false;
            serviceResponse.message = ex.getMessage();
        }
        return serviceResponse;
    }


    @Override
    public ServiceResponse<String> verify(Long id, String secretCode) {
        var serviceResponse=new ServiceResponse<String>();
        try{
            var storedUser=_userRepository.findById(id).orElseThrow(()-> new Exception("User with this email not found"));
            if(storedUser.getVerified()){
                throw new Exception("You have already confirmed your email.");
            }
            var cachedSecretCode=_cacheRepository.getData(CacheEntity.SECRET_CODE.toString(), storedUser.getEmail()).orElseThrow(()->new Exception("Secret code has expired, click resend code button."));
            if(!secretCode.equals(cachedSecretCode)){
                throw new Exception("Wrong secret code.");
            }
            storedUser.setVerified(true);
            _userRepository.save(storedUser);
            serviceResponse.data=null;
            serviceResponse.success=true;
            serviceResponse.message="You have successfully confirmed your email.";
        }catch (Exception ex){
            serviceResponse.data=null;
            serviceResponse.success=false;
            serviceResponse.message=ex.getMessage();
        }
        return serviceResponse;
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
