package uol.compass.vacinpb.dto.form;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

@Data
public class EmployerRegistrationFormDTO {

    @CPF
    @NotEmpty
    private String cpf;

    @NotEmpty
    private String cnes;
}
