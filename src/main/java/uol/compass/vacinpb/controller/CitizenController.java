package uol.compass.vacinpb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uol.compass.vacinpb.dto.CitizenDTO;
import uol.compass.vacinpb.dto.CitizenVaccinesDTO;
import uol.compass.vacinpb.dto.CitizenWithVaccinesDTO;
import uol.compass.vacinpb.dto.form.CitizenFormDTO;
import uol.compass.vacinpb.dto.form.CitizenVaccinesFormDTO;
import uol.compass.vacinpb.service.CitizenService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cidadaos")
public class CitizenController {

    @Autowired
    private CitizenService service;

    //cadastra novos cidadãos
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    @PostMapping
    @Transactional
    public ResponseEntity<CitizenDTO> saveCitizen(@RequestBody @Valid CitizenFormDTO body) {
        CitizenDTO citizen = this.service.save(body);
        return new ResponseEntity<>(citizen, HttpStatus.CREATED);
    }

    //cadastra vacinas em um cidadão
    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    @PostMapping("/{cpf}/vacinas")
    @Transactional
    public ResponseEntity<CitizenVaccinesDTO> addVaccine(@PathVariable String cpf, @RequestBody @Valid CitizenVaccinesFormDTO body) {
        CitizenVaccinesDTO citizenVaccine = this.service.addVaccine(cpf, body);
        return ResponseEntity.ok(citizenVaccine);
    }

    //lista os cidadãos com filtro por nome e idade.
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    @GetMapping
    public ResponseEntity<List<CitizenDTO>> getCitizens(@RequestParam(name = "nome", required = false) String fullName,
                                                        @RequestParam(name = "data-inicial", required = false) @DateTimeFormat(pattern = "ddMMyyyy") LocalDate startDate,
                                                        @RequestParam(name = "data-final", required = false) @DateTimeFormat(pattern = "ddMMyyyy") LocalDate endDate) {
        List<CitizenDTO> citizen = this.service.getCitizens(fullName, startDate, endDate);
        return ResponseEntity.ok(citizen);
    }

    //procura o cidadão pelo seu cpf
    @GetMapping("/{cpf}")
    public ResponseEntity<CitizenDTO> searchCitizen(@PathVariable String cpf){
        CitizenDTO citizen = this.service.searchCitizen(cpf);
        return ResponseEntity.ok(citizen);
    }

    //procura o cidadão pelo seu cpf e mostra suas vacinas
    @GetMapping("/{cpf}/vacinas")
    public ResponseEntity<CitizenWithVaccinesDTO> listCitizenVaccines(@PathVariable String cpf){
        CitizenWithVaccinesDTO citizen = this.service.listCitizenVaccines(cpf);
        return ResponseEntity.ok(citizen);
    }

    //atualiza os dados de um cidadão
    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    @PutMapping("/{cpf}")
    @Transactional
    public ResponseEntity<CitizenDTO> updateCitizen(@PathVariable String cpf, @RequestBody @Valid CitizenFormDTO body) {
        CitizenDTO citizen = this.service.updateCitizen(cpf, body);
        return ResponseEntity.ok(citizen);
    }

    //deleta um cidadão
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{cpf}")
    @Transactional
    public ResponseEntity<?> deleteCitizen(@PathVariable String cpf) {
        this.service.deleteCitizen(cpf);
        return ResponseEntity.noContent().build();
    }
}