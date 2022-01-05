package uol.compass.vacinpb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.vacinpb.entity.CitizenVaccine;

public interface CitizenVaccineRepository extends JpaRepository<CitizenVaccine, Long> {
}
