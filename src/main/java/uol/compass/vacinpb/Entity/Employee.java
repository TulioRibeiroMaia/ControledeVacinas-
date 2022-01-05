package uol.compass.vacinpb.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee extends User{

    private String cnes;

    private String email;

    private String password;
}
