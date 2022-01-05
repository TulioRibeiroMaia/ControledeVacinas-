package uol.compass.vacinpb.dto.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;
import uol.compass.vacinpb.enums.UserRole;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CitizenFormDTO {

    @CPF
    @NotEmpty
    private String cpf;

    @NotEmpty
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @Size(min = 15, max = 15, message = "Tamanho inválido")
    private String cns;

    @NotNull
    private UserRole userRole;
}
