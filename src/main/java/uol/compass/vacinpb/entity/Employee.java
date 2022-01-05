package uol.compass.vacinpb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee extends User{

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "hc_cnes")
    private HealthCenter healthCenter;
}
