package noidea.Dystopia.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import noidea.Dystopia.dto.DystopiaDTO;
import noidea.Dystopia.models.Dystopia;
import noidea.Dystopia.services.DystopiaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class DystopiaMessageController {

    private final DystopiaService dystopiaService;

    @PostMapping("/dystopia_message")
    public String saveDystopiaData(@RequestParam("phone") String phone, DystopiaDTO dystopiaDTO, Model model) {
        model.addAttribute(dystopiaService.createDist(dystopiaDTO));
        // Проверяем, что телефон введен корректно
        if (!isValidPhone(dystopiaDTO.getPhone())) {
            model.addAttribute("error", "Некорректный номер телефона");
            return "redirect:/dystopia_form";
        }
//
//        // Сохраняем данные
//        model.addAttribute(dystopiaService.createDist(dystopiaDTO));

        // Перенаправляем на страницу подтверждения
        return "redirect:/home";
    }

    private boolean isValidPhone(String phone) {
        // Простая проверка формата номера телефона
        return phone.matches("^\\+?[0-9]{10,15}$");
    }
}
//        return "redirect:/page_three";

