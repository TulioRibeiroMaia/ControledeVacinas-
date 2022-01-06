package uol.compass.vacinpb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uol.compass.vacinpb.dto.HealthCenterDTO;
import uol.compass.vacinpb.dto.form.HealthCenterFormDTO;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/postos")
public class HealthCenterController {

    @Autowired
    private HealthCenterService service;

    //cadastra novos postos de saúde
    @PostMapping
    @Transactional
    public ResponseEntity<HealthCenterDTO> saveHealthCenter(@RequestBody @Valid HealthCenterFormDTO body) {
        HealthCenterDTO healthCenter = this.service.save(body);
        return new ResponseEntity<>(healthCenter, HttpStatus.CREATED);
    }

    //lista os postos por nome, estado e cidade.
    @GetMapping
    public ResponseEntity<List<HealthCenterDTO>> getHealthCenter(@RequestParam(required = false) String name, @RequestParam(required = false) String state,
                                                                 @RequestParam(required = false) String city) {
        List<HealthCenterDTO> healthCenter = this.service.getHealthCenter(name, state, city);
        return ResponseEntity.ok(healthCenter);
    }

    //procura o posto pelo cnes
    @GetMapping("/{cnes}")
    public ResponseEntity<HealthCenterDTO> searchHealthCenter(@PathVariable String cnes){
        HealthCenterDTO healthCenter = this.service.searchHealthCenter(cnes);
        return ResponseEntity.ok(healthCenter);
    }

    //atualiza os dados de um posto de saúde
    @PutMapping("/{cnes}")
    @Transactional
    public ResponseEntity<HealthCenterDTO> updateHealthCenter(@PathVariable String cnes, @RequestBody @Valid HealthCenterFormDTO body) {
        HealthCenterDTO healthCenter = this.service.updateHealthCenter(cnes, body);
        return ResponseEntity.ok(healthCenter);
    }

    //deleta um posto de saúde
    @DeleteMapping("/{cnes}")
    @Transactional
    public ResponseEntity<HealthCenterDTO> deleteHealthCenter(@PathVariable String cnes) {
        HealthCenterDTO healthCenter = this.service.deleteHealthCenter(cnes);
        return ResponseEntity.ok(healthCenter);
    }
}