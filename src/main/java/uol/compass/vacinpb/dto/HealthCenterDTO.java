package uol.compass.vacinpb.dto;

import uol.compass.vacinpb.entity.Employee;
import uol.compass.vacinpb.entity.enums.State;

import java.util.List;

public class HealthCenterDTO {

    private String cnes;

    private String name;

    private String city;

    private State state;

    private List<Employee> employees;
}
