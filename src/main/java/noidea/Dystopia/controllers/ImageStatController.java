package noidea.Dystopia.controllers;

import lombok.AllArgsConstructor;
import noidea.Dystopia.models.Audio;
import noidea.Dystopia.models.Image;
import noidea.Dystopia.models.ImageStat;
import noidea.Dystopia.response.ResponseImage;
import noidea.Dystopia.services.ImageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class ImageStatController {

    private final ImageService imageService;
    private final Logger logger = LoggerFactory.getLogger(ImageStatController.class);


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
    public String createImageStat(
            @RequestParam("file1") MultipartFile file1,
            ImageStat imageStat,
            BindingResult bindingResult
    ) throws IOException {
        // Объединение всех проверок в одном месте
        validateFile(file1, bindingResult);

        // Если есть ошибки, немедленно возвращаемся с перенаправлением
        if (bindingResult.hasErrors()) {
            return "redirect:/error_page";
        }

        // Сохранение файла и метаданных
        try {
            imageService.saveImageStat(imageStat, file1);
        } catch (IOException e) {
            logger.error("Ошибка при сохранении файла.", e);
            bindingResult.rejectValue("file1", "error.file", "Ошибка при обработке файла.");
            return "redirect:/error_page";
        }

        return "redirect:/page_four";
    }

    private void validateFile(MultipartFile file1, BindingResult result) {
        // Проверка на пустоту
        if (file1.isEmpty()) {
            result.rejectValue("file1", "error.file", "Пожалуйста, выберите файл.");
            return;
        }

        // Проверка типа файла
        String contentType = file1.getContentType();
        if (!contentType.startsWith("image")) {
            result.rejectValue("file1", "error.file", "Неподдерживаемый формат файла.");
            return;
        }

        // Ограничение размера файла
        long maxSize = 1024 * 1024 * 5; // 5MB
        if (file1.getSize() > maxSize) {
            result.rejectValue("file1", "error.file", "Размер файла превышает допустимый предел.");
            return;
        }
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
//  так картинку ты отрендеришь

    @GetMapping(value = "/images/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageTwo(@PathVariable Long id) {
        // Получаем объект Image через сервис
        byte[] optionalImage = imageService.getImageById(id);
        return ResponseEntity.ok().body(imageService.getImageById(id));
    }
}
