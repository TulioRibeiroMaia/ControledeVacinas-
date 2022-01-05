package uol.compass.vacinpb.dto.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;
import uol.compass.vacinpb.enums.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class EmployeeFormDTO {

    @CPF
    @NotEmpty
    private String cpf;

    @NotEmpty
    private String name;

    @Email
    private String email;

    @NotEmpty
    @Size(min = 8)
    private String password;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @NotNull
    private UserRole userRole;
}
