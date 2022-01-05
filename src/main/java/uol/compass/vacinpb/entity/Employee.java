package uol.compass.vacinpb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.vacinpb.enums.UserRole;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    private String cpf;

    public String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    public UserRole userRole;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "hc_cnes")
    private HealthCenter healthCenter;
}
