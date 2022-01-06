package uol.compass.vacinpb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uol.compass.vacinpb.dto.VaccineDTO;
import uol.compass.vacinpb.dto.form.VaccineFormDTO;
import uol.compass.vacinpb.service.VaccineService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vacinas")
public class VaccineController {

    @Autowired
    private VaccineService service;

    //cadastra novas vacinas
    @PostMapping
    @Transactional
    public ResponseEntity<VaccineDTO> saveVaccine(@RequestBody @Valid VaccineFormDTO body) {
        VaccineDTO vaccine = this.service.save(body);
        return new ResponseEntity<>(vaccine, HttpStatus.CREATED);
    }

    //lista as vacinas por nome,fabricante e número do lote.
    @GetMapping
    public ResponseEntity<List<VaccineDTO>> getVaccines(@RequestParam(required = false) String name, @RequestParam(required = false) String manufacturer,
                                                       @RequestParam(required = false) String lotNumber) {
        List<VaccineDTO> vaccine = this.service.getVaccines(name, manufacturer, lotNumber);
        return ResponseEntity.ok(vaccine);
    }

    //procura o posto pelo id da vacina
    @GetMapping("/{id}")
    public ResponseEntity<VaccineDTO> searchVaccine(@PathVariable Long id){
        VaccineDTO vaccine = this.service.searchVaccine(id);
        return ResponseEntity.ok(vaccine);
    }

    //atualiza os dados de uma vacina referenciado ao id
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VaccineDTO> updateVaccine(@PathVariable Long id, @RequestBody @Valid VaccineFormDTO body) {
        VaccineDTO vaccine = this.service.updateVaccine(id, body);
        return ResponseEntity.ok(vaccine);
    }

    //deleta uma vacina
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<VaccineDTO> deleteVaccine(@PathVariable Long id) {
        VaccineDTO vaccine = this.service.deleteVaccine(id);
        return ResponseEntity.ok(vaccine);
    }
}