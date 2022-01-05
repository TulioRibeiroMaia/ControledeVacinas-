package uol.compass.vacinpb.dto.form;

import uol.compass.vacinpb.enums.State;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class HealthCenterFormDTO {

    @NotEmpty
    private String cnes;

    @NotEmpty
    private String name;

    @NotEmpty
    private String city;

    @NotNull
    private State state;
}
