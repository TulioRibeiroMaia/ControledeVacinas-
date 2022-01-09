package uol.compass.vacinpb.dto.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CitizenVaccinesFormDTO {

    @NotNull
    private Long vaccineId;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate vaccinationDate;
}
