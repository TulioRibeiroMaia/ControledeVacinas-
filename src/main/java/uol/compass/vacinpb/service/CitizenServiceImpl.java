package uol.compass.vacinpb.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.compass.vacinpb.dto.CitizenDTO;
import uol.compass.vacinpb.dto.CitizenVaccinesDTO;
import uol.compass.vacinpb.dto.CitizenWithVaccinesDTO;
import uol.compass.vacinpb.dto.form.CitizenFormDTO;
import uol.compass.vacinpb.dto.form.CitizenVaccinesFormDTO;
import uol.compass.vacinpb.entity.Citizen;
import uol.compass.vacinpb.entity.CitizenVaccines;
import uol.compass.vacinpb.entity.Vaccine;
import uol.compass.vacinpb.exception.ResourceNotFoundException;
import uol.compass.vacinpb.repository.CitizenRepository;
import uol.compass.vacinpb.repository.CitizenVaccinesRepository;
import uol.compass.vacinpb.repository.VaccineRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitizenServiceImpl implements CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private CitizenVaccinesRepository cvr;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CitizenDTO save(CitizenFormDTO body) {
        Citizen citizen = modelMapper.map(body, Citizen.class);
        Citizen savedCitizen = this.citizenRepository.save(citizen);
        return modelMapper.map(savedCitizen, CitizenDTO.class);
    }

    @Override
    public List<CitizenDTO> getCitizens(String fullName, LocalDate startDate, LocalDate endDate) {
        List<Citizen> citizens;

        if (fullName != null) {
            citizens = this.citizenRepository.findByFullNameIgnoreCaseContaining(fullName);
        } else if (startDate != null) {
            if (endDate != null) {
                citizens = this.citizenRepository.findByBirthDateBetween(startDate, endDate);
            } else {
                citizens = this.citizenRepository.findByBirthDateBetween(startDate, LocalDate.now());
            }
        } else {
            citizens = this.citizenRepository.findAll();
        }

        return citizens
                .stream()
                .map(citizen -> modelMapper
                        .map(citizen, CitizenDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CitizenDTO searchCitizen(String cpf) {
        Optional<Citizen> citizen = this.citizenRepository.findByCpf(cpf);

        if (citizen.isPresent()) {
            return modelMapper.map(citizen.get(), CitizenDTO.class);
        }

        throw new ResourceNotFoundException("CPF " + cpf);
    }

    @Override
    public CitizenDTO updateCitizen(String cpf, CitizenFormDTO body) {
        Optional<Citizen> citizen = this.citizenRepository.findByCpf(cpf);

        if (citizen.isPresent()) {
            Citizen updatedCitizen = modelMapper.map(body, Citizen.class);
            updatedCitizen.setId(citizen.get().getId());
            this.citizenRepository.save(updatedCitizen);

            return modelMapper.map(updatedCitizen, CitizenDTO.class);
        }

        throw new ResourceNotFoundException("CPF " + cpf);
    }

    @Override
    public void deleteCitizen(String cpf) {
        Optional<Citizen> citizen = this.citizenRepository.findByCpf(cpf);

        if (!citizen.isPresent()) {
            throw new ResourceNotFoundException("CPF " + cpf);
        }

        this.citizenRepository.deleteById(citizen.get().getId());
    }

    @Override
    public CitizenVaccinesDTO addVaccine(String cpf, CitizenVaccinesFormDTO body) {
        Optional<Citizen> citizenOptional = this.citizenRepository.findByCpf(cpf);
        Optional<Vaccine> vaccineOptional = this.vaccineRepository.findById(body.getVaccineId());
        LocalDate vaccinationDate = body.getVaccinationDate();

        if (!citizenOptional.isPresent()) {
            throw new ResourceNotFoundException("CPF " + cpf);
        }
        if (!vaccineOptional.isPresent()) {
            throw new ResourceNotFoundException("ID " + body.getVaccineId());
        }

        Citizen citizen = citizenOptional.get();
        Vaccine vaccine = vaccineOptional.get();

        CitizenVaccines citizenVaccines = new CitizenVaccines();
        citizenVaccines.setCitizen(citizen);
        citizenVaccines.setVaccine(vaccine);
        citizenVaccines.setVaccinationDate(vaccinationDate);

        cvr.save(citizenVaccines);

        return modelMapper.map(citizenVaccines, CitizenVaccinesDTO.class);
    }

    @Override
    public CitizenWithVaccinesDTO listCitizenVaccines(String cpf) {
        Optional<Citizen> citizen = this.citizenRepository.findByCpf(cpf);

        if (citizen.isPresent()) {
            return modelMapper.map(citizen.get(), CitizenWithVaccinesDTO.class);
        }

        throw new ResourceNotFoundException("CPF " + cpf);
    }
}
