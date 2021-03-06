package uol.compass.vacinpb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.vacinpb.enums.State;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class HealthCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cnes;

    private String name;

    private String city;

    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "healthCenter", cascade = CascadeType.ALL)
    private List<Employee> employees;
}
