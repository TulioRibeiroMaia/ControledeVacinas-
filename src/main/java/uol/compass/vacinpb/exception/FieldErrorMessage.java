package uol.compass.vacinpb.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uol.compass.vacinpb.exception.dto.FieldErrorDTO;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class FieldErrorMessage {
    private int statusCode;
    private Date timestamp;
    private List<FieldErrorDTO> fieldErrors;
    private String description;
}
