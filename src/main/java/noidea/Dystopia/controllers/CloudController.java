package noidea.Dystopia.controllers;

import lombok.AllArgsConstructor;
import noidea.Dystopia.dto.CloudDTO;
import noidea.Dystopia.models.Cloud;
import noidea.Dystopia.services.CloudService;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;


@Controller
//@RestController

//@RequestMapping("/api/clouds")
@CrossOrigin(origins = "http://localhost:8081")
@AllArgsConstructor
public class CloudController {

    private final CloudService cloudService;


    @PostMapping("/cloud_message/post")
    public String postCloudMessage(CloudDTO cloudDTO, Model model) throws IOException {
        model.addAttribute(cloudService.createCloud(cloudDTO));
        return "redirect:/page_sixth";
    }


    @GetMapping("/cloud_message/{id}")
    public String getCloudMessage(@RequestParam("id") Long imageId, Model model) {


        // Получаем объект Cloud по его идентификатору
        Cloud cloud = cloudService.getCloudById(imageId);


        // Извлекаем cloudId из объекта Cloud
        String cloudId = cloud.getCloudId();

        // Передаем cloudId в шаблон
        model.addAttribute("cloudId", cloudId);

        model.addAttribute(cloudService.readCloud());
        return "redirect:/home";
    }

    @PutMapping("/cloud_message/update")
    public String updateCloudMessage(Cloud cloud, Model model) {
        model.addAttribute(cloudService.updateCloud(cloud));
        return "redirect:/page_sixth";
    }

    @DeleteMapping("/cloud_message/delete/{id}")
    public HttpStatus deleteCloudMessage(@PathVariable(value = "id") Long id) {
        cloudService.deleteCloud(id);
        return HttpStatus.OK;
    }
}


