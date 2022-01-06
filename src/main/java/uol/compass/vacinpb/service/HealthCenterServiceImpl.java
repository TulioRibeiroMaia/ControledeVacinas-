package uol.compass.vacinpb.service;

import org.springframework.stereotype.Service;
import uol.compass.vacinpb.dto.HealthCenterDTO;
import uol.compass.vacinpb.dto.form.HealthCenterFormDTO;

import java.util.List;

@Service
public class HealthCenterServiceImpl implements HealthCenterService {
    @Override
    public HealthCenterDTO save(HealthCenterFormDTO body) {
        return null;
    }

    @Override
    public List<HealthCenterDTO> getHealthCenters(String name, String state, String city) {
        return null;
    }

    @Override
    public HealthCenterDTO searchHealthCenter(String cnes) {
        return null;
    }

    @Override
    public HealthCenterDTO updateHealthCenter(String cnes, HealthCenterFormDTO body) {
        return null;
    }

    @Override
    public HealthCenterDTO deleteHealthCenter(String cnes) {
        return null;
    }
}
