package uol.compass.vacinpb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import uol.compass.vacinpb.entity.enums.VaccineName;

import java.time.LocalDate;

@Data
public class VaccineDTO {

    private Long id;

    private VaccineName name;

    private String manufacturer;

    private LocalDate manufactureDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expirationDate;

    private String lotNumber;
}
