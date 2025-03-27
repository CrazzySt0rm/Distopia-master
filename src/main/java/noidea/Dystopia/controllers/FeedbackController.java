package noidea.Dystopia.controllers;

import lombok.AllArgsConstructor;
import noidea.Dystopia.dto.FeedbackDTO;
import noidea.Dystopia.services.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("/feedback/create")
    public String cF(FeedbackDTO feedbackDTO, Model model) {
        model.addAttribute(feedbackService.createFeedback(feedbackDTO));
        return "redirect:/page_seven";
    }
    @GetMapping("/feedback")
    public String getF(Model model) {
        model.addAttribute(feedbackService.readFeedback());
        return "/page_seven";
    }
    @DeleteMapping("/feedback/delete/{id}")
    public HttpStatus deleteF(Long id) {
        feedbackService.deleteFeedback(id);
        return HttpStatus.OK;

    }
}
