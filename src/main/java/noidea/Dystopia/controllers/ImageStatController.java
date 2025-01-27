package noidea.Dystopia.controllers;

import lombok.AllArgsConstructor;
import noidea.Dystopia.models.Image;
import noidea.Dystopia.models.ImageStat;
import noidea.Dystopia.response.ResponseImage;
import noidea.Dystopia.services.ImageService;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class ImageStatController {

    private final ImageService imageService;


    @GetMapping("/")
    public String makeImageSt(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("make_image", imageService.imageStatList(title));
        return "home";
    }

    @GetMapping("/image/{id}")
    public String imageStatInfo(@PathVariable(value = "id") Long id, Model model) {
        ImageStat imageStat = imageService.getImageStatById(id);
        model.addAttribute("image_stat", imageStat);
        model.addAttribute("images", imageStat.getImages());
        return "redirect:/page_four";
    }

    @PostMapping("/image_stat/create")
    public String createImageStat(@RequestParam("file1") MultipartFile file1, ImageStat imageStat) throws IOException {
        imageService.saveImageStat(imageStat, file1);
        //return "redirect:/";
        return "redirect:/page_four";
    }


    @DeleteMapping("/image_stat/delete/{id}")
    public String deleteImageStat(@PathVariable(value = "id") Long id) {
        imageService.deleteImageStat(id);
        return "redirect:/";
    }


    @GetMapping("/images")
    public ResponseEntity<List<ResponseImage>> getListImages() {
        List<ResponseImage> imageList = imageService.getAllImages().map(dbImage -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/images/")
                    .path(String.valueOf(dbImage.getId()))
                    .toUriString();

            return new ResponseImage(
                    dbImage.getName(),
                    fileDownloadUri,
                    dbImage.getContentType(),
                    dbImage.getDescription(),
                    dbImage.getSize(),
                    dbImage.getDateOfCreated(),
                    dbImage.getImageData().length);

        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(imageList);
    }

    //так файл ты скачаешь
    @GetMapping("/images/{id}/x")
    public ResponseEntity<byte[]> getImageOne(@PathVariable Long id, Image image) {
        return imageService.getMyImage(id) //здесь тип опциональный (т.е. может быть Null внутри)
                .map(dbimage -> //мап это как будто ты ифчик делаешь на контейнере изменяя его содержимое (те если внутри чот есть, применится вот этот код)
                        ResponseEntity.ok() //просто красивая обертка для ответа
                                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + id + "\"") // без этой строчки вместо закачки у тебя будет кусок как будто ты изображение в текстовом редакторе открыл
                                .body(image.getImageData()) // в содержимое ответа кладем массив байтов
                ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); //если внутри optional null те он пустой, то мы возвращаем ошибку 404 (
    }
//    //так картинку ты отрендеришь

    @GetMapping(value = "/images/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageTwo(@PathVariable Long id) {
        // Получаем объект Image через сервис
        byte[] optionalImage = imageService.getImageById(id);
        return ResponseEntity.ok().body(imageService.getImageById(id));
    }
}

//    @GetMapping("/{id}")
//    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
//        try {
//            byte[] imageData = imageService.getImageById(id);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.IMAGE_JPEG); // Или другой MIME-тип в зависимости от типа изображения
//            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }



//    //так файл ты скачаешь
//    @GetMapping("/image_stat/{id}")
//    public ResponseEntity<byte[]> getImageOne(@PathVariable Long id) {
//        return imageService.getImageStatById(id) //здесь тип опциональный (т.е. может быть Null внутри)
//                .wait(image -> //мап это как будто ты ифчик делаешь на контейнере изменяя его содержимое (те если внутри чот есть, применится вот этот код)
//                        ResponseEntity.ok() //просто красивая обертка для ответа
//                                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + id + "\"") // без этой строчки вместо закачки у тебя будет кусок как будто ты изображение в текстовом редакторе открыл
//                                .body(imageService.saveImageStat()) // в содержимое ответа кладем массив байтов
//                ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); //если внутри optional null те он пустой, то мы возвращаем ошибку 404 (
//
//    }
//
//    //так картинку ты отрендеришь
//    @GetMapping(value ="/images/{fileId}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public ResponseEntity<byte[]> getFileTwo(@PathVariable Long id, ImageStat imageStat, MultipartFile file1) {
//        try {
//            return new ResponseEntity<>(imageService.saveImageStat());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
////        return new ResponseEntity<>(imageService.getImageStatById(id));
//
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }
//}
