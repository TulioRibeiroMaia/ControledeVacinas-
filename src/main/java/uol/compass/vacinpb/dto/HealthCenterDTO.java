package uol.compass.vacinpb.dto;

import lombok.Data;
import uol.compass.vacinpb.entity.Employee;
import uol.compass.vacinpb.enums.State;

import java.util.List;

@Data
public class HealthCenterDTO {

    private String cnes;

    private String name;

    private String city;

    private State state;

    private List<Employee> employees;
}
