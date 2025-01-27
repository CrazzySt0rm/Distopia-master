package noidea.Dystopia.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import noidea.Dystopia.models.File;
import noidea.Dystopia.repositories.FileRepository;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
@AllArgsConstructor

public class FileService {

    private final FileRepository fileRepository;


    public File store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File File = new File(fileName, file.getContentType(), file.getBytes());

        return fileRepository.save(File);
    }

    public Optional<File> getMyFile(String id) {
        System.out.println("fileID: " + id);
        return fileRepository.findById(id);
    }

    public Stream<File> getAllFiles() {
        return fileRepository.findAll().stream();
    }

    public void deleteFile(String id) {
        fileRepository.deleteById(id);

    }
}

