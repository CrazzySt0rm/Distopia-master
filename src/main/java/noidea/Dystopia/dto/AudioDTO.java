package noidea.Dystopia.dto;

import lombok.Data;

@Data
public class AudioDTO {

    private boolean isPreviewAudio;

    private String audioTitle;
    private String audioDescription;

    private byte[] audioData;
}
