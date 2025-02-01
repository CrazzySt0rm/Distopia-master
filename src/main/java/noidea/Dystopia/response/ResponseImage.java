package noidea.Dystopia.response;

import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseImage {

    private String name;
    private String url;
    private String contentType;
    private long size;





    private LocalDateTime dateOfCreated;

    public ResponseImage(String name, String url, String contentType, long size, LocalDateTime dateOfCreated, int length) {
        this.name = name;
        this.url = url;
        this.contentType = contentType;
        this.size = size;
        this.dateOfCreated = dateOfCreated;

    }
}
