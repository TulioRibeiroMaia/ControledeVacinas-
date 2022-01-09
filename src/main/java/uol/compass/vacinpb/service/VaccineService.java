package uol.compass.vacinpb.service;

import uol.compass.vacinpb.dto.VaccineDTO;
import uol.compass.vacinpb.dto.form.VaccineFormDTO;

import java.util.List;

public interface VaccineService {

    VaccineDTO save(VaccineFormDTO body);

    List<VaccineDTO> getVaccines(String lotNumber, Boolean sortExpDate);

    VaccineDTO searchVaccine(Long id);

    VaccineDTO updateVaccine(Long id, VaccineFormDTO body);

    VaccineDTO deleteVaccine(Long id);
}
