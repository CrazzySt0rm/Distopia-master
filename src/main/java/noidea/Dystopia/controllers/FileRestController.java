//package noidea.Dystopia.controllers;
//
//import lombok.AllArgsConstructor;
//import noidea.Dystopia.services.FileService;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@AllArgsConstructor
//@CrossOrigin(origins = "http://localhost:8081")
//public class FileRestController {
//
//    private final FileService fileService;
//
//
//    @DeleteMapping("/files/delete/{id}")
//    public HttpStatus deleteMyImage(@PathVariable String id) {
//        fileService.deleteImage(id);
//        return HttpStatus.OK;
//    }
//}
