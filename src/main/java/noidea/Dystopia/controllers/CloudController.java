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


import java.io.IOException;


@Controller
//@RestController

//@RequestMapping("/api/clouds")
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


    @GetMapping("/cloud_message/{id}")
    public String getCloudMessage(@PathVariable("id") String imageId, Model model) {
        try {
            Cloud cloud = cloudService.getCloudById(imageId);
            if (cloud == null) {
                throw new RuntimeException("Запись с ID " + imageId + " не найдена");
            }

            String cloudUrl = cloud.getCloudName(); // Полная публичная ссылка на файл
            String fileName = cloud.getCloudName().substring(cloud.getCloudName().lastIndexOf('/') + 1); // Извлекаем имя файла из URL

            model.addAttribute("cloudName", cloudUrl);
            model.addAttribute("fileName", fileName);

            return "redirect:/page_sixth"; // Укажите имя вашего шаблона
        } catch (RuntimeException e) {
            log.error("Ошибка при получении записи по ID: {}", imageId, e);
            model.addAttribute("errorMessage", "Не удалось найти запись с ID " + imageId);
            return "error_page";
        }
    }

    //находим audio по url
    @GetMapping("/cloud_message/{url}")
    public ResponseEntity<String> getCloudUrl(@PathVariable("url") String cloudUrl) {
        try {
            String directUrl = cloudService.getCloudUrl(cloudUrl);
            return ResponseEntity.ok(directUrl);
        } catch (RuntimeException e) {
            log.error("Ошибка при получении записи по URL: {}", cloudUrl, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
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
}


