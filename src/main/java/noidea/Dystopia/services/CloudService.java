package noidea.Dystopia.services;


import lombok.AllArgsConstructor;
import noidea.Dystopia.dto.CloudDTO;
import noidea.Dystopia.models.Cloud;
import noidea.Dystopia.repositories.CloudRepository;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CloudService {

    private final CloudRepository cloudRepository;

    public Cloud createCloud(CloudDTO cloudDTO) {

        String cloudName = cloudDTO.getCloudName(); // Получаем ссылку из DTO

        // Извлекаем cloud_id из ссылки
        String[] parts = cloudName.split("/");
        String cloudId = parts[parts.length - 2];

        // Пытаемся определить MIME-тип файла по расширению
        String mimeType = getMimeTypeFromUrl(cloudName);

        // Создаем новый объект Cloud
        Cloud cloud = Cloud.builder()
                .cloudId(cloudId)          // Устанавливаем cloud_id
                .cloudName(cloudName)      // Устанавливаем полное имя
                .mimeType(mimeType)        // Устанавливаем MIME-тип
                .build();

        // Сохраняем объект в базу данных
        return cloudRepository.save(cloud);
    }

    /**
     * Метод для определения MIME-типа файла по его URL.
     */
    private String getMimeTypeFromUrl(String url) {
        try {
            // Загружаем содержимое файла по ссылке
            URL fileUrl = new URL(url);
            InputStream inputStream = fileUrl.openStream();

            // Определяем MIME-тип с использованием Apache Tika
            Tika tika = new Tika();
            String mimeType = tika.detect(inputStream);

            inputStream.close();
            return mimeType;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cloud> readCloud() {
        return cloudRepository.findAll();
    }

    // Метод для получения объекта Cloud по его идентификатору
    public Cloud getCloudById(Long id) {
        return cloudRepository.findById(id).orElseThrow(() -> new RuntimeException("Запись не найдена"));
    }

    public Cloud updateCloud(Cloud cloud) {
        // Найти существующий объект по id
        Optional<Cloud> existingCloud = cloudRepository.findById(cloud.getId());

        if (existingCloud.isPresent()) {
            Cloud updatedCloud = existingCloud.get();
            updatedCloud.setCloudId(cloud.getCloudId()); // Обновляем cloud_id
            updatedCloud.setCloudName(cloud.getCloudName()); // Обновляем имя

            // Сохраняем изменения
            return cloudRepository.save(updatedCloud);
        } else {
            throw new RuntimeException("Объект не найден!");
        }
    }

    public void deleteCloud(Long id) {
        cloudRepository.deleteById(id);
    }
}