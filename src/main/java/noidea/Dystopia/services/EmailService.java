package noidea.Dystopia.services;

import jakarta.mail.MessagingException;import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import noidea.Dystopia.models.Dystopia;
import noidea.Dystopia.repositories.DystopiaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final DystopiaRepository dystopiaRepository;
    @Value("${spring.mail.username}")
    private String userName;
    public String sendEmail(String to, String subject, String text) throws MailException {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(userName);
            helper.setText(text);
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new MailException("Error sending email", e) {

            };
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
