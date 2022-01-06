package uol.compass.vacinpb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.vacinpb.enums.VaccineName;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VaccineName vaccineName;

    private String manufacturer;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate manufactureDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expirationDate;

    private String lotNumber;

    @OneToMany(mappedBy = "vaccine")
    private List<CitizenVaccines> vaccines;
}
