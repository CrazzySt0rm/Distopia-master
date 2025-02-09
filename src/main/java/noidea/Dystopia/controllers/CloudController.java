package noidea.Dystopia.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import noidea.Dystopia.dto.CloudDTO;
import noidea.Dystopia.models.Cloud;
import noidea.Dystopia.services.CloudService;
import org.springframework.core.io.InputStreamResource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.io.InputStream;

import java.util.Optional;

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

////    @GetMapping("/{fileId}")
////    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId) throws IOException {
////        Cloud cloud  = cloudService.getCloudById(fileId);
////        byte[] fileContent = cloudService.downloadFile(fileInfo.getUrl());
////
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.parseMediaType(fileInfo.getMimeType()));
////        headers.setContentLength(fileContent.length);
////        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
////
////        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
////    }

//@GetMapping("/{id}")
//    public ResponseEntity<Cloud> getCloud(@PathVariable Long id) {
//        Optional<Cloud> cloudOptional = cloudService.getCloudById(id);
//        if (cloudOptional.isPresent()) {
//            return ResponseEntity.ok(cloudOptional.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

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

//    @GetMapping("/{cloudId}")
//    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String cloudId) throws IOException {
//        InputStream inputStream = cloudService.getFileFromDrive(cloudId);
//        String contentType = cloudService.getContentTypeFromDrive(inputStream.readAllBytes());
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .body(new InputStreamResource(inputStream));
//    }
}


