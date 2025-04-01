package noidea.Dystopia.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import noidea.Dystopia.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private final JavaMailSender emailSender;
    private final EmailRepository emailRepository;

    @Value("${email_tuz_word_app}")
    private String senderAddress;

    public EmailService(JavaMailSender emailSender, EmailRepository emailRepository) {
        this.emailSender = emailSender;
        this.emailRepository = emailRepository;
    }

    public void sendSimpleMessage(String to, String subject, String body) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderAddress);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);
    }

    public void sendEmailWithAttachment(String to, String subject, String text, MultipartFile attachment)
            throws MailException, MessagingException, IOException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(senderAddress);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        if (attachment != null && !attachment.isEmpty()) {
            ByteArrayResource byteArrayResource = new ByteArrayResource(attachment.getBytes());
            helper.addAttachment(attachment.getOriginalFilename(), byteArrayResource);
        }

        emailSender.send(mimeMessage);
    }
}

