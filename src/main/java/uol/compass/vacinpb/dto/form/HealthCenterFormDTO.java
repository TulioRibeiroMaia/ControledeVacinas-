package uol.compass.vacinpb.dto.form;

import lombok.Data;
import uol.compass.vacinpb.enums.State;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
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
