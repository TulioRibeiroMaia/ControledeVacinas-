package uol.compass.vacinpb.dto.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.vacinpb.enums.VaccineName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineFormDTO {

    @NotNull(message = "Nome inválido: verifique a lista de vacinas ofertadas pelo SUS")
    private VaccineName vaccineName;

    @NotEmpty
    private String manufacturer;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate manufactureDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate expirationDate;

    @NotEmpty
    private String lotNumber;
}
