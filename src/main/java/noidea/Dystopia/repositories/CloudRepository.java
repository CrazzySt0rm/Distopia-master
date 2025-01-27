package noidea.Dystopia.repositories;

import noidea.Dystopia.models.Cloud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudRepository extends JpaRepository<Cloud, Long> {
}
