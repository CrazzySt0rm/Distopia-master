package noidea.Dystopia.controllers;

import lombok.AllArgsConstructor;
import noidea.Dystopia.dto.DystopiaDTO;
import noidea.Dystopia.models.Dystopia;
import noidea.Dystopia.services.DystopiaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class DystopiaRestController {

    private final DystopiaService dystopiaService;

    @PostMapping("/distopia/create")
    public ResponseEntity createDis(@RequestBody DystopiaDTO dystopiaDTO) {
        return new ResponseEntity<>(dystopiaService.createDist(dystopiaDTO), HttpStatus.OK);
    }
    @GetMapping("/distopia")
    public ResponseEntity readAllDis() {
        return new ResponseEntity<>(dystopiaService.readAllDist(), HttpStatus.OK);
    }
    @PutMapping("/distopia/update")
    public ResponseEntity updateDis(@RequestBody Dystopia dystopia) {
        return new ResponseEntity<>(dystopiaService.updateDist(dystopia), HttpStatus.OK);
    }
    @DeleteMapping("/distopia/{id}")
    public HttpStatus deleteDis(@PathVariable(value = "id") Long id) {
        dystopiaService.deleteDist(id);
        return HttpStatus.OK;
    }



}
