package uol.compass.vacinpb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CitizenVaccinesDTO {

    private Long id;

    private VaccineDTO vaccine;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate vaccinationDate;
}
