package uol.compass.vacinpb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import uol.compass.vacinpb.entity.Vaccine;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class CitizenDTO implements Serializable {

    private String cpf;

    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private String cns;

    private List<VaccineRecordDTO> vaccines;
}
