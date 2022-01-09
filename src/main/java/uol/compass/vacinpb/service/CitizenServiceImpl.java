package uol.compass.vacinpb.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.compass.vacinpb.dto.CitizenDTO;
import uol.compass.vacinpb.dto.CitizenVaccinesDTO;
import uol.compass.vacinpb.dto.form.CitizenFormDTO;
import uol.compass.vacinpb.dto.form.CitizenVaccinesFormDTO;
import uol.compass.vacinpb.entity.Citizen;
import uol.compass.vacinpb.entity.CitizenVaccines;
import uol.compass.vacinpb.entity.Vaccine;
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
    public List<CitizenDTO> getCitizens(String name) {
        List<Citizen> citizens = this.citizenRepository.findAll();

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

        // substituir pela exceção específica assim que implementar o handler
        throw new RuntimeException("Resource Not Found Exception");
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

        // substituir pela exceção específica assim que implementar o handler
        throw new RuntimeException("Resource Not Found Exception");
    }

    @Override
    public CitizenDTO deleteCitizen(String cpf) {
        Optional<Citizen> citizen = this.citizenRepository.findByCpf(cpf);

        if (citizen.isPresent()) {
            this.citizenRepository.deleteById(citizen.get().getId());
            return modelMapper.map(citizen.get(), CitizenDTO.class);
        }

        // substituir pela exceção específica assim que implementar o handler
        throw new RuntimeException("Resource Not Found Exception");
    }

    @Override
    public CitizenVaccinesDTO addVaccine(String cpf, CitizenVaccinesFormDTO body) {
        Optional<Citizen> citizenOptional = this.citizenRepository.findByCpf(cpf);
        Optional<Vaccine> vaccineOptional = this.vaccineRepository.findById(body.getVaccineId());
        LocalDate vaccinationDate = body.getVaccinationDate();

        if (!citizenOptional.isPresent()) {
            // substituir pela exceção específica assim que implementar o handler
            throw new RuntimeException("Resource Not Found Exception - CPF");
        }
        if (!vaccineOptional.isPresent()) {
            // substituir pela exceção específica assim que implementar o handler
            throw new RuntimeException("Resource Not Found Exception - ID vacina");
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
}
