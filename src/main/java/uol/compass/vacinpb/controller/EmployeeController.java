package uol.compass.vacinpb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uol.compass.vacinpb.dto.EmployeeDTO;
import uol.compass.vacinpb.dto.form.EmployeeFormDTO;
import uol.compass.vacinpb.dto.form.EmployerRegistrationFormDTO;
import uol.compass.vacinpb.service.EmployeeService;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/funcionarios")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    //cadastra novos funcionários
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Transactional
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody @Valid EmployeeFormDTO body) {
        EmployeeDTO employee = this.service.save(body);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    //registra um novo empregado em um posto(UBS)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{cpf}/unidades")
    @Transactional
    public ResponseEntity<EmployeeDTO> registerEmployee(@PathVariable String cpf, @RequestBody @Valid EmployerRegistrationFormDTO body) {
        EmployeeDTO employeeRegistered = this.service.registerEmployee(cpf, body);
        return ResponseEntity.ok(employeeRegistered);
    }

    //lista os empregados com filtro por nome.
    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees(@RequestParam(name = "nome", required = false) String fullName) {
        List<EmployeeDTO> employee = this.service.getEmployees(fullName);
        return ResponseEntity.ok(employee);
    }

    //procura o empregado pelo seu cpf
    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    @GetMapping("/{cpf}")
    public ResponseEntity<EmployeeDTO> searchEmployee(@PathVariable String cpf){
        EmployeeDTO employee = this.service.searchEmployee(cpf);
        return ResponseEntity.ok(employee);
    }

    //atualiza os dados de um empregado
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{cpf}")
    @Transactional
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable String cpf, @RequestBody @Valid EmployeeFormDTO body) {
        EmployeeDTO employee = this.service.updateEmployee(cpf, body);
        return ResponseEntity.ok(employee);
    }

    //deleta um empregado
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{cpf}")
    @Transactional
    public ResponseEntity<?> deleteEmployee(@PathVariable String cpf) {
        this.service.deleteEmployee(cpf);
        return ResponseEntity.noContent().build();
    }
}