package uol.compass.vacinpb.dto.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerRegistrationFormDTO {

    @NotEmpty
    private String cnes;
}
