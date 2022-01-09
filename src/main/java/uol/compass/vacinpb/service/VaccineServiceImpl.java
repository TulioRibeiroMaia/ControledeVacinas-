package uol.compass.vacinpb.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.compass.vacinpb.dto.VaccineDTO;
import uol.compass.vacinpb.dto.VaccineDTO;
import uol.compass.vacinpb.dto.form.VaccineFormDTO;
import uol.compass.vacinpb.dto.form.VaccineFormDTO;
import uol.compass.vacinpb.entity.Vaccine;
import uol.compass.vacinpb.repository.VaccineRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VaccineServiceImpl implements VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public VaccineDTO save(VaccineFormDTO body) {
        Vaccine vaccine = modelMapper.map(body, Vaccine.class);
        Vaccine savedVaccine = this.vaccineRepository.save(vaccine);
        return modelMapper.map(savedVaccine, VaccineDTO.class);
    }

    @Override
    public List<VaccineDTO> getVaccines(String lotNumber, Boolean sortExpDate) {
        List<Vaccine> vaccines;

        if (lotNumber == null) {
            vaccines = this.vaccineRepository.findAll();
        } else {
            vaccines = this.vaccineRepository.findByLotNumberIgnoreCase(lotNumber);
        }

        if (Boolean.TRUE.equals(sortExpDate)) {
            vaccines.sort(Comparator.comparing(Vaccine::getExpirationDate).reversed());
        }

        return vaccines
                .stream()
                .map(vaccine -> modelMapper
                        .map(vaccine, VaccineDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public VaccineDTO searchVaccine(Long id) {
        Optional<Vaccine> vaccine = this.vaccineRepository.findById(id);

        if (vaccine.isPresent()) {
            return modelMapper.map(vaccine.get(), VaccineDTO.class);
        }

        // substituir pela exceção específica assim que implementar o handler
        throw new RuntimeException("Resource Not Found Exception");
    }

    @Override
    public VaccineDTO updateVaccine(Long id, VaccineFormDTO body) {
        Optional<Vaccine> vaccine = this.vaccineRepository.findById(id);

        if (vaccine.isPresent()) {
            Vaccine updatedVaccine = modelMapper.map(body, Vaccine.class);
            updatedVaccine.setId(id);
            this.vaccineRepository.save(updatedVaccine);

            return modelMapper.map(updatedVaccine, VaccineDTO.class);
        }

        // substituir pela exceção específica assim que implementar o handler
        throw new RuntimeException("Resource Not Found Exception");
    }

    @Override
    public VaccineDTO deleteVaccine(Long id) {
        Optional<Vaccine> vaccine = this.vaccineRepository.findById(id);

        if (vaccine.isPresent()) {
            this.vaccineRepository.deleteById(id);
            return modelMapper.map(vaccine.get(), VaccineDTO.class);
        }

        // substituir pela exceção específica assim que implementar o handler
        throw new RuntimeException("Resource Not Found Exception");
    }
}
