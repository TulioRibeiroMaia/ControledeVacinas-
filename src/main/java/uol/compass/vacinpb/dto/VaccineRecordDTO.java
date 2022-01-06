package uol.compass.vacinpb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccineRecordDTO {

    private Long id;

    private VaccineDTO vaccine;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate vaccinationDate;
}
