package noidea.Dystopia.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import noidea.Dystopia.dto.DystopiaDTO;
import noidea.Dystopia.models.Dystopia;
import noidea.Dystopia.repositories.DystopiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class DystopiaService {

    private final DystopiaRepository dystopiaRepository;


    public Dystopia createDist(DystopiaDTO dystopiaDTO) {
        return dystopiaRepository.save(Dystopia.builder()
                .name(dystopiaDTO.getName())
                .phone(dystopiaDTO.getPhone())
                .message(dystopiaDTO.getMessage())
                .gender(dystopiaDTO.getGender())
                .studentCourses(dystopiaDTO.getStudentCourses())
                .build());

    }

    public List<Dystopia> readAllDist() {
        return dystopiaRepository.findAll();
    }

    public Dystopia updateDist(Dystopia dystopia) {
        return dystopiaRepository.save(dystopia);
    }

    public void deleteDist(Long id) {
        dystopiaRepository.deleteById(id);
    }
}
