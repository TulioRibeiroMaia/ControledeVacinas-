package uol.compass.vacinpb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.vacinpb.entity.Vaccine;
import uol.compass.vacinpb.enums.VaccineName;


import java.util.List;
import java.util.Optional;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    Optional<Vaccine> findById(Long id);

    List<Vaccine> findByLotNumberIgnoreCase(String lotNumber);
}
