package uol.compass.vacinpb.service;

import uol.compass.vacinpb.dto.CitizenDTO;
import uol.compass.vacinpb.dto.CitizenVaccinesDTO;
import uol.compass.vacinpb.dto.CitizenWithVaccinesDTO;
import uol.compass.vacinpb.dto.form.CitizenFormDTO;
import uol.compass.vacinpb.dto.form.CitizenVaccinesFormDTO;

import java.time.LocalDate;
import java.util.List;

public interface CitizenService {

    CitizenDTO save(CitizenFormDTO body);

    List<CitizenDTO> getCitizens(String fullName, LocalDate startDate, LocalDate endDate);

    CitizenDTO searchCitizen(String cpf);

    CitizenDTO updateCitizen(String cpf, CitizenFormDTO body);

    CitizenDTO deleteCitizen(String cpf);

    CitizenVaccinesDTO addVaccine(String cpf, CitizenVaccinesFormDTO body);

    CitizenWithVaccinesDTO listCitizenVaccines(String cpf);
}
