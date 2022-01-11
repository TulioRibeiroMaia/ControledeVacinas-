package uol.compass.vacinpb.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.vacinpb.enums.UserRole;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cpf;

    public String fullName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate birthDate;

    @Enumerated(EnumType.STRING)

    public UserRole userRole = UserRole.USUARIO;

    private String cns;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "citizen", cascade = CascadeType.ALL)
    private List<CitizenVaccines> vaccines;
}
