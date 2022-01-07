package uol.compass.vacinpb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.vacinpb.entity.HealthCenter;

import java.util.List;
import java.util.Optional;

public interface HealthCenterRepository extends JpaRepository<HealthCenter, Long> {

    Optional<HealthCenter> findByCnes(String cnes);

    Optional<HealthCenter> findByName(String name);

    List<HealthCenter> findByCity(String city);

    List<HealthCenter> findByState(String state);

    void deleteByCnes(String cnes);
}
