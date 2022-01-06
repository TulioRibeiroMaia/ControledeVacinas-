package uol.compass.vacinpb.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CitizenVaccines {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate vacinationDate;

    @ManyToOne
    @JoinColumn(name = "citizen_cpf")
    private Citizen citizen;

    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;
}
