package noidea.Dystopia.controllers;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class ProxyController {

    private final OkHttpClient client = new OkHttpClient();

    @GetMapping("/proxy/{fileId}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fileId) throws IOException {

        String url = "https://drive.google.com/uc?export=view&id=" + fileId;
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "Mozilla/5.0")
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to fetch image from Google Drive: " + response.message());
            }

            byte[] bytes = response.body().bytes();
            MediaType contentType = MediaType.parseMediaType(response.header("Content-Type"));

            CacheControl cacheControl = CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic(); // кэшируем на 1 час

            return ResponseEntity.ok()
                    .contentType(contentType)
                    .cacheControl(cacheControl)
                    .body(bytes);
        }
    }
}


