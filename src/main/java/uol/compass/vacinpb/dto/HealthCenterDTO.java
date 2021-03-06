package uol.compass.vacinpb.dto;

import lombok.Data;
import uol.compass.vacinpb.enums.State;

@Data
public class HealthCenterDTO {

    private Long id;

    private String cnes;

    private String name;

    private String city;

    private State state;
}
