package noidea.Dystopia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import java.time.LocalDateTime;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    private String originalFileName;

    private String name;

    private long size;

    private String contentType;

    private boolean isPreviewImage;

    @Lob
    private byte[] imageData;

//    private String url;

    private LocalDateTime dateOfCreated;



    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }


    private String description;



    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private ImageStat imageStat;

    public String generateBase64Image() {
        return Base64.encodeBase64String(this.imageData);
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateOfCreated() {
        return dateOfCreated;
    }

    public void setDateOfCreated(LocalDateTime dateOfCreated) {
        this.dateOfCreated = dateOfCreated;
    }

}




