package noidea.Dystopia.controllers;

import lombok.AllArgsConstructor;
import noidea.Dystopia.services.DystopiaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class DystopiaRestMessageController {

    private final DystopiaService dystopiaService;

    @GetMapping("/dystopia_message")
    public ResponseEntity getDystMessage() {
        return new ResponseEntity<>(dystopiaService.readAllDist(), HttpStatus.OK);
    }
}
