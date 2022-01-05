package uol.compass.vacinpb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.vacinpb.entity.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByCpf(String cpf);

    Optional<Employee> findByName(String name);
}