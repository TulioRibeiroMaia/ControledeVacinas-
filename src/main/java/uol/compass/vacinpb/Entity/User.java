package uol.compass.vacinpb.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import uol.compass.vacinpb.Enums.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDate;

public abstract class User {

    @Id
    private String cpf;

    public String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    public Role role;
}
