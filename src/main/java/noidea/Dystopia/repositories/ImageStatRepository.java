package noidea.Dystopia.repositories;

import noidea.Dystopia.models.ImageStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageStatRepository extends JpaRepository<ImageStat, Long> {

    List<ImageStat> findByTitle(String title);
}
