package noidea.Dystopia.services;

import lombok.AllArgsConstructor;
import noidea.Dystopia.dto.AudioDTO;
import noidea.Dystopia.models.Audio;
import noidea.Dystopia.repositories.AudioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class AudioService {

    private final AudioRepository audioRepository;

    public void saveAudio(AudioDTO audioDTO, MultipartFile file) throws IOException {
        Audio audio = new Audio();
        audio.setAudioTitle(audioDTO.getAudioTitle());
        audio.setAudioDescription(audioDTO.getAudioDescription());
        audio.setAudioData(file.getBytes());
        audioRepository.save(audio);
    }

    /**
     * Возвращает аудиофайл по его идентификатору.
     *
     * @param id Идентификатор аудиофайла.
     * @return Аудиофайл.
     */
    public Audio getAudioById(Long id) {
        return audioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Audio not found"));
    }

    /**
     * Удаляет аудиофайл по его идентификатору.
     *
     * @param id Идентификатор аудиофайла.
     */
    public void deleteAudio(Long id) {
        audioRepository.deleteById(id);
    }

    /**
     * Возвращает бинарные данные аудиофайла по его идентификатору.
     *
     * @param id Идентификатор аудиофайла.
     * @return Бинарные данные аудиофайла.
     */
    public byte[] getAudioDataById(Long id) {
        Audio audio = getAudioById(id);
        return audio.getAudioData();
    }
}
