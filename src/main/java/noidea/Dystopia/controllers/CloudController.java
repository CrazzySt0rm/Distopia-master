package noidea.Dystopia.controllers;

import lombok.AllArgsConstructor;
import noidea.Dystopia.dto.CloudDTO;
import noidea.Dystopia.models.Cloud;
import noidea.Dystopia.services.CloudService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import java.io.IOException;


@Controller
//@RestController

//@RequestMapping("/cloud")
@CrossOrigin(origins = "http://localhost:8081")
@AllArgsConstructor
public class CloudController {

    private final CloudService cloudService;
    private static final Logger log = LoggerFactory.getLogger(CloudController.class);


    @PostMapping("/cloud_message/post")
    public String postCloudMessage(CloudDTO cloudDTO, Model model) throws IOException {
        model.addAttribute(cloudService.createCloud(cloudDTO));
        return "redirect:/page_sixth";
    }

    @GetMapping("/cloud_message/cloud_id/")
    public String getCloudMessage(@RequestParam("cloud_id") String cloudId, Model model) {
        try {
            Cloud cloud = cloudService.getCloudById(cloudId);
            if (cloud != null) {
                model.addAttribute("imageUrl", cloud.getCloudName()); // Добавляем ссылку на изображение
                return "/page_seven"; // Переходим на страницу с изображением
            } else {
                throw new RuntimeException("Запись с ID " + cloudId + " не найдена");
            }
        } catch (RuntimeException e) {
            log.error("Ошибка при получении записи по ID: {}", cloudId, e);
            model.addAttribute("errorMessage", "Не удалось найти запись с ID " + cloudId);
            return "error_page";
        }
    }


//    @GetMapping("/cloud_message/cloud_id")
//    public String getCloudMessage(@PathVariable("cloud_id") String cloudId, Model model) {
//        try {
//            Cloud cloud = cloudService.getCloudById(cloudId);
//
//            if (cloud != null) {
//                model.addAttribute("cloudName", cloud.getCloudName()); // Передача полной ссылки на изображение
//
//                return "page_sixth"; // Переход на страницу с изображением
//            } else {
//                throw new RuntimeException("Запись с ID " + cloudId + " не найдена");
//            }
//        } catch (RuntimeException e) {
//            log.error("Ошибка при получении записи по ID: {}", cloudId, e);
//            model.addAttribute("errorMessage", "Не удалось найти запись с ID " + cloudId);
//            return "error_page";
//        }
//    }

    @GetMapping("/cloud/{fileId}/pdf")
    public RedirectView downloadPdfFile(@PathVariable String fileId) {
        String url = "https://drive.google.com/uc?id=" + fileId + "&export=download";
        return new RedirectView(url);
    }

    @PutMapping("/cloud_message/update")
    public String updateCloudMessage(Cloud cloud, Model model) {
        model.addAttribute(cloudService.updateCloud(cloud));
        return "redirect:/page_sixth";
    }

    @DeleteMapping("/cloud_message/delete/{id}")
    public HttpStatus deleteCloudMessage(@PathVariable(value = "id") String id) {
        cloudService.deleteCloud(id);
        return HttpStatus.OK;
    }

    @PostMapping("/cloud_message")
    public String showCloudMessage(@RequestParam("cloud_name") String cloudName, Model model) {
        // Проверяем, что ссылка не пустая
        if (cloudName != null && !cloudName.isEmpty()) {
            // Передаем ссылку в модель для отображения на странице
            model.addAttribute(cloudService.getCloudUrl(cloudName));
            return "page_seven"; // Переход на страницу с отображением файла
        } else {
            model.addAttribute("errorMessage", "Пожалуйста, введите ссылку на файл.");
            return "error_page"; // Переход на страницу с ошибкой
        }
    }
}


