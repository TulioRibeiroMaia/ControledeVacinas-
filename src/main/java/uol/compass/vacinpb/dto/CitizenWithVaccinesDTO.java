package uol.compass.vacinpb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CitizenWithVaccinesDTO {

    private Long id;

    private String cpf;

    private String fullName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private String cns;

    private List<CitizenVaccinesDTO> vaccines;
}
