package uol.compass.vacinpb.service;

import uol.compass.vacinpb.dto.EmployeeDTO;
import uol.compass.vacinpb.dto.form.EmployeeFormDTO;
import uol.compass.vacinpb.dto.form.EmployerRegistrationFormDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO save(EmployeeFormDTO body);

    List<EmployeeDTO> getEmployees(String name);

    EmployeeDTO searchEmployee(String cpf);

    EmployeeDTO updateEmployee(String cpf, EmployeeFormDTO body);

    EmployeeDTO deleteEmployee(String cpf);

    EmployeeDTO registerEmployee(EmployerRegistrationFormDTO body);
}
