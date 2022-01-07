package uol.compass.vacinpb.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.compass.vacinpb.dto.EmployeeDTO;
import uol.compass.vacinpb.dto.EmployeeDTO;
import uol.compass.vacinpb.dto.form.EmployeeFormDTO;
import uol.compass.vacinpb.dto.form.EmployeeFormDTO;
import uol.compass.vacinpb.dto.form.EmployerRegistrationFormDTO;
import uol.compass.vacinpb.entity.Employee;
import uol.compass.vacinpb.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public EmployeeDTO save(EmployeeFormDTO body) {
        Employee employee = modelMapper.map(body, Employee.class);
        Employee savedEmployee = this.employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getEmployees(String name) {
        List<Employee> employees = this.employeeRepository.findAll();

        return employees
                .stream()
                .map(employee -> modelMapper
                        .map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO searchEmployee(String cpf) {
        Optional<Employee> employee = this.employeeRepository.findByCpf(cpf);

        if (employee.isPresent()) {
            return modelMapper.map(employee.get(), EmployeeDTO.class);
        }

        // substituir pela exceção específica assim que implementar o handler
        throw new RuntimeException("Resource Not Found Exception");
    }

    @Override
    public EmployeeDTO updateEmployee(String cpf, EmployeeFormDTO body) {
        Optional<Employee> employee = this.employeeRepository.findByCpf(cpf);

        if (employee.isPresent()) {
            Employee updatedEmployee = modelMapper.map(body, Employee.class);
            updatedEmployee.setCpf(cpf);
            this.employeeRepository.save(updatedEmployee);

            return modelMapper.map(updatedEmployee, EmployeeDTO.class);
        }

        // substituir pela exceção específica assim que implementar o handler
        throw new RuntimeException("Resource Not Found Exception");
    }

    @Override
    public EmployeeDTO deleteEmployee(String cpf) {
        Optional<Employee> employee = this.employeeRepository.findByCpf(cpf);

        if (employee.isPresent()) {
            this.employeeRepository.deleteByCpf(cpf);
            return modelMapper.map(employee.get(), EmployeeDTO.class);
        }

        // substituir pela exceção específica assim que implementar o handler
        throw new RuntimeException("Resource Not Found Exception");
    }

    @Override
    public EmployeeDTO registerEmployee(EmployerRegistrationFormDTO body) {
        return null;
    }
}
