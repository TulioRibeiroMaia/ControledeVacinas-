package uol.compass.vacinpb.service;

import org.springframework.stereotype.Service;
import uol.compass.vacinpb.dto.EmployeeDTO;
import uol.compass.vacinpb.dto.form.EmployeeFormDTO;
import uol.compass.vacinpb.dto.form.EmployerRegistrationFormDTO;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public EmployeeDTO save(EmployeeFormDTO body) {
        return null;
    }

    @Override
    public List<EmployeeDTO> getEmployees(String name) {
        return null;
    }

    @Override
    public EmployeeDTO searchEmployee(String cpf) {
        return null;
    }

    @Override
    public EmployeeDTO updateEmployee(String cpf, EmployeeFormDTO body) {
        return null;
    }

    @Override
    public EmployeeDTO deleteEmployee(String cpf) {
        return null;
    }

    @Override
    public EmployeeDTO registerEmployee(EmployerRegistrationFormDTO body) {
        return null;
    }
}
