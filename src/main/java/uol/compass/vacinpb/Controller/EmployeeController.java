package uol.compass.vacinpb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uol.compass.vacinpb.dto.EmployeeDTO;
import uol.compass.vacinpb.dto.form.EmployeeFormDTO;
import uol.compass.vacinpb.dto.form.EmployerRegistrationFormDTO;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/funcionários")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    //cadastra novos funcionários
    @PostMapping
    @Transactional
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody @Valid EmployeeFormDTO body) {
        EmployeeDTO employee = this.service.save(body);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    //registra um novo empregado em um posto(UBS)
    @PostMapping("/postos")
    @Transactional
    public ResponseEntity<EmployeeDTO> registerEmployee(@RequestBody @Valid EmployerRegistrationFormDTO body) {
        EmployeeDTO employeeRegistered = this.service.registerEmployee(body);
        return ResponseEntity.ok(employeeRegistered);
    }

    //lista os empregados com filtro por nome.
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployee(@RequestParam(required = false) String name) {
        List<EmployeeDTO> employee = this.service.getEmployee(name);
        return ResponseEntity.ok(employee);
    }

    //procura o empregado pelo seu cpf
    @GetMapping("/{cpf}")
    public ResponseEntity<EmployeeDTO> searchEmployee(@PathVariable String cpf){
        EmployeeDTO employee = this.service.searchEmployee(cpf);
        return ResponseEntity.ok(employee);
    }

    //atualiza os dados de um empregado
    @PutMapping("/{cpf}")
    @Transactional
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable String cpf, @RequestBody @Valid EmployeeFormDTO body) {
        EmployeeDTO employee = this.service.updateEmployee(cpf, body);
        return ResponseEntity.ok(employee);
    }

    //deleta um empregado
    @DeleteMapping("/{cpf}")
    @Transactional
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable String cpf) {
        EmployeeDTO employee = this.service.deleteEmployee(cpf);
        return ResponseEntity.ok(employee);
    }
}