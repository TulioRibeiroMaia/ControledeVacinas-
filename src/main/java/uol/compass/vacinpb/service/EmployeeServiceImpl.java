package uol.compass.vacinpb.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.compass.vacinpb.dto.EmployeeDTO;
import uol.compass.vacinpb.dto.form.EmployeeFormDTO;
import uol.compass.vacinpb.dto.form.EmployerRegistrationFormDTO;
import uol.compass.vacinpb.entity.Employee;
import uol.compass.vacinpb.entity.HealthCenter;
import uol.compass.vacinpb.repository.EmployeeRepository;
import uol.compass.vacinpb.repository.HealthCenterRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HealthCenterRepository hcRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeDTO save(EmployeeFormDTO body) {
        Employee employee = modelMapper.map(body, Employee.class);
        Employee savedEmployee = this.employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getEmployees(String fullName) {
        List<Employee> employees;

        if (fullName != null) {
            employees = this.employeeRepository.findByFullNameIgnoreCaseContaining(fullName);
        } else {
            employees = this.employeeRepository.findAll();
        }

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
            updatedEmployee.setId(employee.get().getId());
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
            this.employeeRepository.deleteById(employee.get().getId());
            return modelMapper.map(employee.get(), EmployeeDTO.class);
        }

        // substituir pela exceção específica assim que implementar o handler
        throw new RuntimeException("Resource Not Found Exception");
    }

    @Override
    public EmployeeDTO registerEmployee(String cpf, EmployerRegistrationFormDTO body) {
        Optional<Employee> employeeOptional = employeeRepository.findByCpf(cpf);
        Optional<HealthCenter> hcOptional = hcRepository.findByCnes(body.getCnes());

        if (!employeeOptional.isPresent()) {
            // substituir pela exceção específica assim que implementar o handler
            throw new RuntimeException("Resource Not Found Exception - CPF");
        }
        if (!hcOptional.isPresent()) {
            // substituir pela exceção específica assim que implementar o handler
            throw new RuntimeException("Resource Not Found Exception - CNES");
        }

        Employee employee = employeeOptional.get();
        HealthCenter healthCenter = hcOptional.get();

        employee.setHealthCenter(healthCenter);
        this.employeeRepository.save(employee);

        return modelMapper.map(employee, EmployeeDTO.class);
    }
}
