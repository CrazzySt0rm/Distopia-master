package noidea.Dystopia.controllers;

import lombok.AllArgsConstructor;
import noidea.Dystopia.dto.AudioDTO;
import noidea.Dystopia.models.Audio;
import noidea.Dystopia.services.AudioService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.logging.Logger;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class AudioController {

    private final AudioService audioService;


    // Метод для получения информации о MP3-файле
    @GetMapping("/audio/{id}")
    public String audioInfo(@PathVariable(value = "id") Long id, Model model) {
        Audio audio = audioService.getAudioById(id);
        model.addAttribute("audio", audio);
        return "redirect:/page_four";
    }

    // Метод для создания нового MP3-файла
    @PostMapping("/audio/create")
    public String createAudio(@RequestParam("file") MultipartFile file, AudioDTO audioDTO) throws IOException {
        audioService.saveAudio(audioDTO, file);
        return "redirect:/page_four";
    }

    // Метод для удаления MP3-файла
    @DeleteMapping("/audio/delete/{id}")
    public String deleteAudio(@PathVariable(value = "id") Long id) {
        audioService.deleteAudio(id);
        return "redirect:/page_four";
    }

    // Метод для скачивания MP3-файла
    @GetMapping("/audio/download/{id}")
    public ResponseEntity<byte[]> downloadAudio(@PathVariable Long id) {
        byte[] audioData = audioService.getAudioDataById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"audio_" + id + ".mp3\"")
                .contentLength(audioData.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(audioData);
    }

    // Метод для прослушивания MP3-файла
    @GetMapping("/audio/listen/{id}")
    public ResponseEntity<byte[]> listenAudio(@PathVariable Long id) {
        try {
            byte[] audioData = audioService.getAudioDataById(id);
            return ResponseEntity.ok()
                    .contentLength(audioData.length)
                    .contentType(new MediaType("audio", "mpeg"))
                    .body(audioData);
        } catch (Exception e) {
            // Логируйте ошибку
//            log.error("Ошибка при получении аудиофайла: ", e);
            // Возвращайте ошибку клиенту
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

