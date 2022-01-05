package uol.compass.vacinpb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import uol.compass.vacinpb.entity.enums.UserRole;

import java.time.LocalDate;

@Data
public class EmployeeDTO {

    private String cpf;

    private String name;

    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private UserRole userRole;

    private HealthCenterDTO healthCenter;
}
