package uol.compass.vacinpb.dto.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import uol.compass.vacinpb.enums.VaccineName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class VaccineFormDTO {

    @NotNull
    private VaccineName name;

    @NotEmpty
    private String manufacturer;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate manufactureDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expirationDate;

    @NotEmpty
    private String lotNumber;
}
