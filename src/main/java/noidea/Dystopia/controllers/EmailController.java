package noidea.Dystopia.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import noidea.Dystopia.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor
public class EmailController {

    private static final Logger log = LoggerFactory.getLogger(EmailController.class);

    private final EmailService emailService;

    @Value("${my_email}")
    private String myMail;
//    private final String friendEmail;

    @GetMapping("/send-mail")
    public ModelAndView showForm() {
        return new ModelAndView("email-form");
    }

    @PostMapping("/send-mail")
    public String sendEmail(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("body") String body,
            @RequestParam("attachment") MultipartFile attachment
    ) throws Exception {
        if (!attachment.isEmpty()) {
            emailService.sendEmailWithAttachment(to, subject, body, attachment);
        } else {
            emailService.sendSimpleMessage(to, subject, body);
        }
        return "redirect:/page_four";
    }
}




