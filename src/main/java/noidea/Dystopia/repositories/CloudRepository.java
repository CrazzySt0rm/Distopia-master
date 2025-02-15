package noidea.Dystopia.repositories;

import noidea.Dystopia.models.Cloud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CloudRepository extends JpaRepository<Cloud, String> {

    Optional<Cloud> findByCloudId(String cloudId);
    Optional<Cloud> findByCloudName(String cloudName);
}
