package uol.compass.vacinpb.dto.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EmployerRegistrationFormDTO {

    @NotEmpty
    private String cnes;
}
