//package noidea.Dystopia.controllers;
//
//import jakarta.mail.Multipart;
//import lombok.AllArgsConstructor;
//import noidea.Dystopia.dto.ImageDTO;
//import noidea.Dystopia.dto.ImgTestOneDTO;
//import noidea.Dystopia.services.ImageService;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@Controller
//@AllArgsConstructor
//@CrossOrigin(origins = "http://localhost:8081")
//public class NotStrongController {
//
//    private final ImageService imageService;
//
//    @PostMapping("/imgTestOne/save")
//    public String saveMyCoolImg(@RequestBody ImgTestOneDTO imgTestOneDTO, Model model, @RequestParam("img") MultipartFile img) throws IOException {
//        model.addAttribute(imageService.saveImage(imgTestOneDTO));
//        return "redirect:/page_sixth";
//    }
//
//    @GetMapping("/imgTestOne")
//    public String getMyCoolImg(@RequestBody String img, Model model) {
//        model.addAttribute("img", img);
//        return "redirect:/page_sixth";
//    }
//}
