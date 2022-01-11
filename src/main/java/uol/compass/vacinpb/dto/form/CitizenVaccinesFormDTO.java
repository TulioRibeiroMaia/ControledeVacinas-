package uol.compass.vacinpb.dto.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitizenVaccinesFormDTO {

    @NotNull
    private Long vaccineId;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate vaccinationDate;
}
