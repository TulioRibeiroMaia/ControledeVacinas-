package uol.compass.vacinpb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import uol.compass.vacinpb.entity.enums.UserRole;

import java.time.LocalDate;

public class EmployeeDTO {

    private String cpf;

    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private UserRole userRole;

    private HealthCenterDTO healthCenter;
}
