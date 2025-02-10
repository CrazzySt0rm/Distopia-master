package noidea.Dystopia.controllers;

import lombok.AllArgsConstructor;
import noidea.Dystopia.dto.DystopiaDTO;
import noidea.Dystopia.services.DystopiaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class DystopiaMessageController {

    private final DystopiaService dystopiaService;

    @PostMapping("/dystopia_message")
    public String createDystopiaText(DystopiaDTO dystopiaDTO, Model model) {
        model.addAttribute(dystopiaService.createDist(dystopiaDTO));
        return "redirect:/page_three";
    }
}
