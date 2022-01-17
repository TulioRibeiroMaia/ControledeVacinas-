package uol.compass.vacinpb.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uol.compass.vacinpb.dto.EmployeeDTO;
import uol.compass.vacinpb.dto.form.EmployeeFormDTO;
import uol.compass.vacinpb.dto.form.EmployerRegistrationFormDTO;
import uol.compass.vacinpb.enums.UserRole;
import uol.compass.vacinpb.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class EmployeeServiceImplTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    @DisplayName("Deve salvar um funcionário")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void saveTest() {
        EmployeeFormDTO employeeForm = new EmployeeFormDTO(
                "70075570661", "Anna Justina Ferreira Nery", "anery@email.com",
                "qwertyuiop", LocalDate.of(1980, 12, 13), UserRole.ADMIN);

        EmployeeDTO savedEmployee = this.employeeService.save(employeeForm);

        assertNotNull(savedEmployee);
        assertEquals(2L, savedEmployee.getId());
        assertEquals("70075570661", savedEmployee.getCpf());
    }

    @Test
    @DisplayName("Deve retornar uma lista de funcionários")
    void getEmployeesTest() {
        List<EmployeeDTO> employees = this.employeeService.getEmployees(null);

        assertEquals(1, employees.size());
        assertEquals("25117428021", employees.get(0).getCpf());
    }

    @Test
    @DisplayName("Deve retornar uma lista de funcionários ao procurar por nome cadastrado")
    void getEmployeesByNameTest() {
        List<EmployeeDTO> employees = this.employeeService.getEmployees("carlos");

        assertEquals(1, employees.size());
        assertEquals("25117428021", employees.get(0).getCpf());
    }

    @Test
    @DisplayName("Deve retornar um funcionário ao procurar por um CPF cadastrado")
    void searchEmployeeTest() {
        EmployeeDTO employee = this.employeeService.searchEmployee("25117428021");

        assertNotNull(employee);
        assertEquals(1L, employee.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção Resource Not Found ao procurar por um CPF não cadastrado")
    void searchEmployeeByInvalidCpfTest() {
        assertThrows(ResourceNotFoundException.class, () -> this.employeeService.searchEmployee("00000000000"));
    }

    @Test
    @DisplayName("Deve atualizar o funcionário ao informar um CPF cadastrado")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateEmployeeTest() {
        EmployeeFormDTO employeeForm = new EmployeeFormDTO(
                "25117428021", "Carlos Chagas", "chagas@email.com",
                "87654321", LocalDate.of(1979, 9, 7), UserRole.ADMIN);

        EmployeeDTO employee = this.employeeService.updateEmployee("25117428021", employeeForm);

        assertNotNull(employee);
        assertEquals(1L, employee.getId());
        assertEquals("Carlos Chagas", employee.getName());
    }

    @Test
    @DisplayName("Deve lançar exceção Resource Not Found ao tentar atualizar funcionário informando um CPF não cadastrado")
    void updateEmployeeWithInvalidCpfTest() {
        assertThrows(ResourceNotFoundException.class, () -> this.employeeService.updateEmployee("00000000000", null));
    }

    @Test
    @DisplayName("Deve remover funcionário ao informar um CPF cadastrado")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteEmployeeTest() {
        assertEquals(1, this.employeeService.getEmployees(null).size());

        EmployeeDTO employee = this.employeeService.deleteEmployee("25117428021");

        assertEquals(1L, employee.getId());
        assertEquals(0, this.employeeService.getEmployees(null).size());
    }

    @Test
    @DisplayName("Deve lançar exceção Resource Not Found ao tentar remover funcionário informando um CPF não cadastrado")
    void deleteEmployeeWithInvalidCpfTest() {
        assertThrows(ResourceNotFoundException.class, () -> this.employeeService.deleteEmployee("00000000000"));
    }

    @Test
    @DisplayName("Deve matricular o funcionário em uma unidade de saúde")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void registerEmployeeTest() {
        EmployerRegistrationFormDTO registrationForm = new EmployerRegistrationFormDTO("2695464");
        EmployeeDTO employee = this.employeeService.registerEmployee("25117428021", registrationForm);

        assertEquals(1L, employee.getHealthCenter().getId());
        assertEquals("2695464", employee.getHealthCenter().getCnes());
    }

    @Test
    @DisplayName("Deve lançar exceção Resource Not Found ao tentar matricular funcionário informando CPF inválido")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void registerEmployeeWithInvalidCpfTest() {
        EmployerRegistrationFormDTO registrationForm = new EmployerRegistrationFormDTO("2695464");

        assertThrows(ResourceNotFoundException.class, () -> this.employeeService.registerEmployee("00000000000", registrationForm));
    }

    @Test
    @DisplayName("Deve lançar exceção Resource Not Found ao tentar matricular funcionário informando CNES inválido")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void registerEmployeeWithInvalidCnesTest() {
        EmployerRegistrationFormDTO registrationForm = new EmployerRegistrationFormDTO("0000000");

        assertThrows(ResourceNotFoundException.class, () -> this.employeeService.registerEmployee("25117428021", registrationForm));
    }
}