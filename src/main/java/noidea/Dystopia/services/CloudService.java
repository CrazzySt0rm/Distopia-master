package noidea.Dystopia.services;

import lombok.AllArgsConstructor;
import noidea.Dystopia.dto.CloudDTO;
import noidea.Dystopia.models.Cloud;
import noidea.Dystopia.repositories.CloudRepository;
import org.springframework.stereotype.Service;

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

        // Создаем новый объект Cloud
        Cloud cloud = Cloud.builder()
                .cloudId(cloudId) // Устанавливаем cloud_id
                .cloudName(cloudName) // Устанавливаем полное имя
                .build();

        // Сохраняем объект в базу данных
        return cloudRepository.save(cloud);
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
