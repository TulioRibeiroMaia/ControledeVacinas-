package uol.compass.vacinpb.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.compass.vacinpb.dto.CitizenDTO;
import uol.compass.vacinpb.dto.form.CitizenFormDTO;
import uol.compass.vacinpb.entity.Citizen;
import uol.compass.vacinpb.repository.CitizenRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitizenServiceImpl implements CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CitizenDTO save(CitizenFormDTO body) {
        Citizen citizen = modelMapper.map(body, Citizen.class);
        Citizen savedCitizen = this.citizenRepository.save(citizen);
        return modelMapper.map(savedCitizen, CitizenDTO.class);
    }

    @Override
    public List<CitizenDTO> getCitizens() {
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
            updatedCitizen.setCpf(cpf);
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
            this.citizenRepository.deleteByCpf(cpf);
            return modelMapper.map(citizen.get(), CitizenDTO.class);
        }

        // substituir pela exceção específica assim que implementar o handler
        throw new RuntimeException("Resource Not Found Exception");
    }
}
