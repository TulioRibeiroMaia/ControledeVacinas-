package uol.compass.vacinpb.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class CitizenVaccines {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate vaccinationDate;

    @ManyToOne
    @JoinColumn(name = "citizen_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Citizen citizen;

    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Vaccine vaccine;
}
