package uol.compass.vacinpb.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.vacinpb.Enums.State;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HealthCenter {

    @Id
    private String cnes;

    private String name;

    private String city;

    @Enumerated(EnumType.STRING)
    private State state;


}
