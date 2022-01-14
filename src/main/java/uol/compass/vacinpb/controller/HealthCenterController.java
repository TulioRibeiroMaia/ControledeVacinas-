package uol.compass.vacinpb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uol.compass.vacinpb.dto.HealthCenterDTO;
import uol.compass.vacinpb.dto.HealthCenterEmployeesDTO;
import uol.compass.vacinpb.dto.form.HealthCenterFormDTO;
import uol.compass.vacinpb.service.HealthCenterService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/unidades")
public class HealthCenterController {

    @Autowired
    private HealthCenterService service;

    //cadastra novos postos de saúde
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Transactional
    public ResponseEntity<HealthCenterDTO> saveHealthCenter(@RequestBody @Valid HealthCenterFormDTO body) {
        HealthCenterDTO healthCenter = this.service.save(body);
        return new ResponseEntity<>(healthCenter, HttpStatus.CREATED);
    }

    //lista os postos por nome, estado e cidade.
    @GetMapping
    public ResponseEntity<List<HealthCenterDTO>> getHealthCenters(@RequestParam(name = "nome", required = false) String name,
                                                                  @RequestParam(name = "uf", required = false) String state,
                                                                  @RequestParam(name = "cidade", required = false) String city) {
        List<HealthCenterDTO> healthCenter = this.service.getHealthCenters(name, state, city);
        return ResponseEntity.ok(healthCenter);
    }

    //procura o posto pelo cnes
    @GetMapping("/{cnes}")
    public ResponseEntity<HealthCenterDTO> searchHealthCenter(@PathVariable String cnes){
        HealthCenterDTO healthCenter = this.service.searchHealthCenter(cnes);
        return ResponseEntity.ok(healthCenter);
    }

    //procura o posto pelo cnes e mostra funcionários
    @PreAuthorize("hasRole('ADMIN','FUNCIONARIO')")
    @GetMapping("/{cnes}/funcionarios")
    public ResponseEntity<HealthCenterEmployeesDTO> listHealthCenterEmployees(@PathVariable String cnes){
        HealthCenterEmployeesDTO healthCenter = this.service.listHealthCenterEmployees(cnes);
        return ResponseEntity.ok(healthCenter);
    }

    //atualiza os dados de um posto de saúde
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{cnes}")
    @Transactional
    public ResponseEntity<HealthCenterDTO> updateHealthCenter(@PathVariable String cnes, @RequestBody @Valid HealthCenterFormDTO body) {
        HealthCenterDTO healthCenter = this.service.updateHealthCenter(cnes, body);
        return ResponseEntity.ok(healthCenter);
    }

    //deleta um posto de saúde
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{cnes}")
    @Transactional
    public ResponseEntity<HealthCenterDTO> deleteHealthCenter(@PathVariable String cnes) {
        HealthCenterDTO healthCenter = this.service.deleteHealthCenter(cnes);
        return ResponseEntity.ok(healthCenter);
    }
}