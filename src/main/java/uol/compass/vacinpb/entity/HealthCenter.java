package uol.compass.vacinpb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.vacinpb.entity.enums.State;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "healthCenter", cascade = CascadeType.ALL)
    private List<Employee> employees;
}
