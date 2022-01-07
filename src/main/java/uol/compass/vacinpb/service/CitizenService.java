package uol.compass.vacinpb.service;

import uol.compass.vacinpb.dto.CitizenDTO;
import uol.compass.vacinpb.dto.VaccineRecordDTO;
import uol.compass.vacinpb.dto.form.CitizenFormDTO;
import uol.compass.vacinpb.dto.form.VaccineRecordFormDTO;

import java.util.List;

public interface CitizenService {

    CitizenDTO save(CitizenFormDTO body);

    List<CitizenDTO> getCitizens(String name);

    CitizenDTO searchCitizen(String cpf);

    CitizenDTO updateCitizen(String cpf, CitizenFormDTO body);

    CitizenDTO deleteCitizen(String cpf);

    VaccineRecordDTO addVaccine(VaccineRecordFormDTO body);
}
