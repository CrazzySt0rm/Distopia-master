package noidea.Dystopia.response;

import java.time.LocalDateTime;

public class ResponseFile {

    private String name;
    private String url;
    private String contentType;
    private String description;
    private long size;





    private LocalDateTime dateOfCreated;

    public ResponseFile(String name, String url, String contentType, String description, long size, LocalDateTime dateOfCreated, int length) {
        this.name = name;
        this.url = url;
        this.contentType = contentType;
        this.description = description;
        this.size = size;
        this.dateOfCreated = dateOfCreated;

    }

}

