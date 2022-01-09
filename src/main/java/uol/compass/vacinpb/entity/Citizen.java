package uol.compass.vacinpb.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import uol.compass.vacinpb.enums.UserRole;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data
public class Citizen {

    public String fullName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    public UserRole userRole = UserRole.USUARIO;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String cpf;
    private String cns;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "citizen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CitizenVaccines> vaccines;
}
