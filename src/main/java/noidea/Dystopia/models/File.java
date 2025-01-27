package noidea.Dystopia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.GenericGenerator;

import java.time.Clock;
import java.time.LocalDateTime;

@Entity
@Table(name = "files")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class File {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String originalFileName;

    private String name;

    private long size;

    private String contentType;

    private String description;

    private boolean isPreviewImage;

    @Lob
    private byte[] fileData;

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {

        dateOfCreated = LocalDateTime.now(Clock.systemDefaultZone());
    }

    public File(String fileName, String contentType, byte[] imageData) {
    }

    public String generateBase64Image() {
        return Base64.encodeBase64String(this.fileData);
    }

}

