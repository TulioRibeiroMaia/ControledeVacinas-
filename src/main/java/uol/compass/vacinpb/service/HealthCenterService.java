package uol.compass.vacinpb.service;

import uol.compass.vacinpb.dto.HealthCenterDTO;
import uol.compass.vacinpb.dto.HealthCenterEmployeesDTO;
import uol.compass.vacinpb.dto.form.HealthCenterFormDTO;

import java.util.List;

public interface HealthCenterService {

    HealthCenterDTO save(HealthCenterFormDTO body);

    List<HealthCenterDTO> getHealthCenters(String name, String state, String city);

    HealthCenterDTO searchHealthCenter(String cnes);

    HealthCenterDTO updateHealthCenter(String cnes, HealthCenterFormDTO body);

    HealthCenterDTO deleteHealthCenter(String cnes);

    HealthCenterEmployeesDTO listHealthCenterEmployees(String cnes);
}
