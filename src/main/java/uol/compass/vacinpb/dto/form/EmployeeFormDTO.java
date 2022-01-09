package uol.compass.vacinpb.dto.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import uol.compass.vacinpb.enums.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class EmployeeFormDTO {

    @CPF
    @NotEmpty
    private String cpf;

    @NotEmpty
    private String fullName;

    @Email
    private String email;

    @NotEmpty
    @Size(min = 8)
    private String password;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate birthDate;

    @NotNull
    private UserRole userRole;
}
