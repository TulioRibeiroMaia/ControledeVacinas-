package uol.compass.vacinpb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.vacinpb.entity.CitizenVaccines;

public interface CitizenVaccinesRepository extends JpaRepository<CitizenVaccines, String> {
}
