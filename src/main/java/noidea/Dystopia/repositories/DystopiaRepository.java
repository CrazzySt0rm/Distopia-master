package noidea.Dystopia.repositories;

import noidea.Dystopia.models.Dystopia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DystopiaRepository extends JpaRepository<Dystopia, Long> {
}
