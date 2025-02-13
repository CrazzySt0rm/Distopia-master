package noidea.Dystopia.controllers;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import noidea.Dystopia.dto.DystopiaDTO;
import noidea.Dystopia.services.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class EmailController {

    private final EmailService emailService;

    @Value("${other_email}")
    private static String friendMail;

    @PostMapping("/create_email")
    public String sdMail(Model model, DystopiaDTO dystopiaDTO) throws MessagingException {

        model.addAttribute(emailService.sendEmail(friendMail,"TestTwo", dystopiaDTO.getMessage()));
        return "page_four";
    }

    @GetMapping("/mail")
    public String getMail() {
        return "redirect:/page_four";
    }
}
