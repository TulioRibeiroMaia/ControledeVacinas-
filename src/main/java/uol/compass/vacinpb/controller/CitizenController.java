package uol.compass.vacinpb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uol.compass.vacinpb.dto.CitizenDTO;
import uol.compass.vacinpb.dto.CitizenVaccinesDTO;
import uol.compass.vacinpb.dto.form.CitizenFormDTO;
import uol.compass.vacinpb.dto.form.CitizenVaccinesFormDTO;
import uol.compass.vacinpb.service.CitizenService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidadaos")
public class CitizenController {

    @Autowired
    private CitizenService service;

    //cadastra novos cidadãos
    @PostMapping
    @Transactional
    public ResponseEntity<CitizenDTO> saveCitizen(@RequestBody @Valid CitizenFormDTO body) {
        CitizenDTO citizen = this.service.save(body);
        return new ResponseEntity<>(citizen, HttpStatus.CREATED);
    }

    @PostMapping("/{cpf}/vacinas")
    @Transactional
    public ResponseEntity<CitizenVaccinesDTO> addVaccine(@PathVariable String cpf, @RequestBody @Valid CitizenVaccinesFormDTO body) {
        CitizenVaccinesDTO citizenVaccine = this.service.addVaccine(cpf, body);
        return ResponseEntity.ok(citizenVaccine);
    }

    //lista os cidadãos com filtro por nome. (Falta implementar o filtro por idade)
    @GetMapping
    public ResponseEntity<List<CitizenDTO>> getCitizen(@RequestParam(name = "nome", required = false) String fullName) {
        List<CitizenDTO> citizen = this.service.getCitizens(fullName);
        return ResponseEntity.ok(citizen);
    }

    //procura o cidadão pelo seu cpf
    @GetMapping("/{cpf}")
    public ResponseEntity<CitizenDTO> searchCitizen(@PathVariable String cpf){
        CitizenDTO citizen = this.service.searchCitizen(cpf);
        return ResponseEntity.ok(citizen);
    }

    //atualiza os dados de um cidadão
    @PutMapping("/{cpf}")
    @Transactional
    public ResponseEntity<CitizenDTO> updateCitizen(@PathVariable String cpf, @RequestBody @Valid CitizenFormDTO body) {
        CitizenDTO citizen = this.service.updateCitizen(cpf, body);
        return ResponseEntity.ok(citizen);
    }

    //deleta um cidadão
    @DeleteMapping("/{cpf}")
    @Transactional
    public ResponseEntity<CitizenDTO> deleteCitizen(@PathVariable String cpf) {
        CitizenDTO citizen = this.service.deleteCitizen(cpf);
        return ResponseEntity.ok(citizen);
    }
}