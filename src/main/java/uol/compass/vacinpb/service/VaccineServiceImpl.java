package uol.compass.vacinpb.service;

import org.springframework.stereotype.Service;
import uol.compass.vacinpb.dto.VaccineDTO;
import uol.compass.vacinpb.dto.form.VaccineFormDTO;

import java.util.List;

@Service
public class VaccineServiceImpl implements VaccineService {
    @Override
    public VaccineDTO save(VaccineFormDTO body) {
        return null;
    }

    @Override
    public List<VaccineDTO> getVaccines(String name, String manufacturer, String lotNumber) {
        return null;
    }

    @Override
    public VaccineDTO searchVaccine(Long id) {
        return null;
    }

    @Override
    public VaccineDTO updateVaccine(Long id, VaccineFormDTO body) {
        return null;
    }

    @Override
    public VaccineDTO deleteVaccine(Long id) {
        return null;
    }
}
