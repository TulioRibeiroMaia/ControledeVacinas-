package uol.compass.vacinpb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.vacinpb.entity.Citizen;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {

    Optional<Citizen> findByCpf(String cpf);

    Optional<Citizen> findByName(String name);

    List<Citizen> findByBirthDate(LocalDate birthDate);

    void deleteByCpf(String cpf);
}
