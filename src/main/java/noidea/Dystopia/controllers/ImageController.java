//package noidea.Dystopia.controllers;
//
//import lombok.AllArgsConstructor;
//import noidea.Dystopia.models.Image;
//import noidea.Dystopia.services.FileService;
//import noidea.Dystopia.services.ImageService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@Controller
//@CrossOrigin(origins = "http://localhost:8081")
//@AllArgsConstructor
//
//public class ImageController {
//
//    private final ImageService imageService;
//
////
////    public ImageController(FileService fileService) {
////        this.fileService = fileService;
////    }
////
////    @GetMapping("/view-image/{fileOid}")
////    public String viewImage(@PathVariable String fileOid, Model model) {
////        Image image = fileService.getFile(fileOid).get();
////        model.addAttribute("fileOid", fileOid);
////        model.addAttribute("image", image);
////        return "page_sixth";
////    }
//
//    @GetMapping("/")
//    public String makeImageT(@RequestParam(name = "title", required = false) String title, Model model) {
//        model.addAttribute("make_image", imageService.imageList(title));
//        return "home";
//    }
//    @GetMapping("/images/{id}")
//    public String imageInfo(@PathVariable(value = "id") Long id, Model model) {
//        Image image = imageService.getImageById(id);
//        model.addAttribute("images", image);
////        model.addAttribute("image", imageStat.getImages());
//        return "redirect:/page_four";
//    }
//    @PostMapping("/images/create")
//    public String createImageT(@RequestParam("file1") MultipartFile file1, Image image) throws IOException {
//        imageService.saveImage(image, file1);
//        return "redirect:/page_four";
//    }
//
//    @DeleteMapping("/images/delete/{id}")
//    public String deleteImageT(@PathVariable(value = "id") Long id) {
//        imageService.deleteImage(id);
//        return "redirect:/";
//    }
//}
//
