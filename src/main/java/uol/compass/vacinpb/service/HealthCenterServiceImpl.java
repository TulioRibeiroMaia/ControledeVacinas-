package uol.compass.vacinpb.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.compass.vacinpb.dto.HealthCenterDTO;
import uol.compass.vacinpb.dto.HealthCenterDTO;
import uol.compass.vacinpb.dto.form.HealthCenterFormDTO;
import uol.compass.vacinpb.dto.form.HealthCenterFormDTO;
import uol.compass.vacinpb.entity.HealthCenter;
import uol.compass.vacinpb.repository.HealthCenterRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HealthCenterServiceImpl implements HealthCenterService {
    
    @Autowired
    private HealthCenterRepository healthCenterRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public HealthCenterDTO save(HealthCenterFormDTO body) {
        HealthCenter healthCenter = modelMapper.map(body, HealthCenter.class);
        HealthCenter savedHealthCenter = this.healthCenterRepository.save(healthCenter);
        return modelMapper.map(savedHealthCenter, HealthCenterDTO.class);
    }

    @Override
    public List<HealthCenterDTO> getHealthCenters(String name, String state, String city) {
        List<HealthCenter> healthCenters = this.healthCenterRepository.findAll();

        return healthCenters
                .stream()
                .map(healthCenter -> modelMapper
                        .map(healthCenter, HealthCenterDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public HealthCenterDTO searchHealthCenter(String cnes) {
        Optional<HealthCenter> healthCenter = this.healthCenterRepository.findByCnes(cnes);

        if (healthCenter.isPresent()) {
            return modelMapper.map(healthCenter.get(), HealthCenterDTO.class);
        }

        // substituir pela exceção específica assim que implementar o handler
        throw new RuntimeException("Resource Not Found Exception");
    }

    @Override
    public HealthCenterDTO updateHealthCenter(String cnes, HealthCenterFormDTO body) {
        Optional<HealthCenter> healthCenter = this.healthCenterRepository.findByCnes(cnes);

        if (healthCenter.isPresent()) {
            HealthCenter updatedHealthCenter = modelMapper.map(body, HealthCenter.class);
            updatedHealthCenter.setCnes(cnes);
            this.healthCenterRepository.save(updatedHealthCenter);

            return modelMapper.map(updatedHealthCenter, HealthCenterDTO.class);
        }

        // substituir pela exceção específica assim que implementar o handler
        throw new RuntimeException("Resource Not Found Exception");
    }

    @Override
    public HealthCenterDTO deleteHealthCenter(String cnes) {
        Optional<HealthCenter> healthCenter = this.healthCenterRepository.findByCnes(cnes);

        if (healthCenter.isPresent()) {
            this.healthCenterRepository.deleteByCnes(cnes);
            return modelMapper.map(healthCenter.get(), HealthCenterDTO.class);
        }

        // substituir pela exceção específica assim que implementar o handler
        throw new RuntimeException("Resource Not Found Exception");
    }
}