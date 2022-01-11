package uol.compass.vacinpb.dto.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.vacinpb.enums.State;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
