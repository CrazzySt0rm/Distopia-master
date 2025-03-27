package noidea.Dystopia.repositories;

import noidea.Dystopia.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
