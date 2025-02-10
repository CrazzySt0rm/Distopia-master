package noidea.Dystopia.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import noidea.Dystopia.models.Image;
import noidea.Dystopia.models.ImageStat;
import noidea.Dystopia.repositories.ImageRepository;
import noidea.Dystopia.repositories.ImageStatRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;


@Service
@AllArgsConstructor
public class ImageService {

    public final ImageStatRepository imageStatRepository;
    public  final ImageRepository imageRepository;


    public List<ImageStat> imageStatList(String title) {
        if (title != null) return imageStatRepository.findByTitle(title);
        return imageStatRepository.findAll();
    }

    public Stream<Image> getAllImages() {
        return imageRepository.findAll().stream();
    }

    public Optional<Image> getMyImage(Long id) {
        System.out.println("id: " + id);
        return imageRepository.findById(id);
    }

    private final static Logger log = Logger.getLogger(ImageStat.class.getName());

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setImageData(file.getBytes());
        return image;
    }

    public ImageStat saveImageStat(ImageStat imageStat, MultipartFile file1) throws IOException {
        Image image1;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            imageStat.addImageToImageStat(image1);
        }
        log.info(imageStat.getTitle());
        ImageStat imageStatFromDb = imageStatRepository.save(imageStat);
        imageStatFromDb.setPreviewImageId(imageStatFromDb.getImages().get(0).getId());

        return imageStatRepository.save(imageStat);
    }

    public void deleteImageStat(Long id) {
        imageStatRepository.deleteById(id);
    }

    public ImageStat getImageStatById(Long id) {
        return imageStatRepository.findById(id).orElse(null);
    }

    @Transactional
    public byte[] getImageById(Long id) {

        ImageStat imageStat = imageStatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        // Находим превью-изображение
        Optional<Image> previewImage = imageStat.getImages()
                .stream()
                .filter(Image::isPreviewImage)
                .findFirst();

        // Если найдено, возвращаем его данные
        if (previewImage.isPresent()) {
            return previewImage.get().getImageData();
        } else {
            throw new RuntimeException("Preview image not found");
        }
    }
}


