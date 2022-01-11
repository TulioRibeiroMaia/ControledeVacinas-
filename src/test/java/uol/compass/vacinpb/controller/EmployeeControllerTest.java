package uol.compass.vacinpb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uol.compass.vacinpb.dto.form.EmployeeFormDTO;
import uol.compass.vacinpb.dto.form.EmployerRegistrationFormDTO;
import uol.compass.vacinpb.enums.UserRole;
import uol.compass.vacinpb.service.EmployeeServiceImpl;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
                .build();
    }

    @Test
    @DisplayName("Deveria cadastrar novos funcionários")
    public void shouldSaveANewEmployee() throws Exception {
        EmployeeFormDTO employeeFormDTO = new EmployeeFormDTO("25117428021", "Carlos Justiniano Ribeiro Chagas", "cchagas@email.com", "12345678", LocalDate.now(), UserRole.FUNCIONARIO);

        mockMvc.perform(MockMvcRequestBuilders.post("/funcionarios")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(employeeFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Deveria cadastrar novos funcionários em um posto de saúde")
    public void shouldSaveANewEmployeeInAHealthCenter() throws Exception {
        EmployerRegistrationFormDTO employerRegistrationFormDTOFormDTO = new EmployerRegistrationFormDTO("2695464");

        mockMvc.perform(MockMvcRequestBuilders.post("/funcionarios/25117428021/unidades")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(employerRegistrationFormDTOFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria retornar todos os funcionarios")
    public void shouldListAllEmployees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/funcionarios"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria encontrar um funcionario pelo seu nome e retornar status OK")
    public void shouldListEmployeeByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/funcionarios?nome=Anna"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria encontrar um funcionario pelo seu CPF e retornar status OK")
    public void shouldLisAnEmployeeByCpf() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders.get("/funcionarios/70075570661")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria atualizar os dados de um funcionario pelo seu CPF")
    public void shouldUpdateAnEmployeeByCpf() throws Exception {
        EmployeeFormDTO employeeFormDTO = new EmployeeFormDTO("25117428021", "Carlos Justiniano Ribeiro Chaga", "cchagas@email.com", "12345678", LocalDate.now(), UserRole.FUNCIONARIO);

        mockMvc.perform(MockMvcRequestBuilders.put("/funcionarios/25117428021")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(employeeFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria deletar um funcionario pelo seu CPF")
    public void shouldDeleteAnEmployeeByCpf() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/funcionarios/25117428021"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}