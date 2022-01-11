package uol.compass.vacinpb.dto.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitizenFormDTO {

    @CPF
    @NotEmpty
    private String cpf;

    @NotEmpty
    private String fullName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate birthDate;

    @Size(min = 15, max = 15, message = "Tamanho inválido")
    private String cns;
}
