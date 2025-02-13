package noidea.Dystopia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "audio")
@Data

public class Audio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean isPreviewAudio;

    private String audioTitle;
    private String audioDescription;
    private byte[] audioData;


    @ManyToOne
    private ImageStat imageStat;

    public Audio() {}

    public Audio(boolean isPreviewAudio, byte[] audioData, ImageStat imageStat) {
        this.isPreviewAudio = isPreviewAudio;
        this.audioData = audioData;
        this.imageStat = imageStat;
    }
}
