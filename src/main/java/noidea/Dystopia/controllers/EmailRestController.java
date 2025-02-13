package noidea.Dystopia.controllers;

import lombok.RequiredArgsConstructor;
import noidea.Dystopia.services.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class EmailRestController {
    private final EmailService emailService;

    @Value("${my_email}")
    private static String myCoolEmail;

    @GetMapping("/send-email")    public String sendEmail() {
        try {            emailService.sendEmail(myCoolEmail, "Test", "1000 и одна ночь");
            return "Email send successfully";        } catch (Exception e) {
            return "Error sending email: " + e.getMessage();
        }
    }
}
