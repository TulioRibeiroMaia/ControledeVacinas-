package uol.compass.vacinpb.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.vacinpb.Enums.VaccineName;

import javax.persistence.*;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VaccineName name;

    private String manufacturer;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate manufactureDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expirationDate;

    private BigInteger lotNumber;
}
