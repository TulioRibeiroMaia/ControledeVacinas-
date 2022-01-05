package uol.compass.vacinpb.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Citizen extends User{

    private String cns;

    @ManyToMany
    private List<Vaccine> vaccines;

}
