package uol.compass.vacinpb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uol.compass.vacinpb.dto.form.EmployeeFormDTO;
import uol.compass.vacinpb.dto.form.HealthCenterFormDTO;
import uol.compass.vacinpb.enums.State;
import uol.compass.vacinpb.enums.UserRole;
import uol.compass.vacinpb.service.EmployeeServiceImpl;
import uol.compass.vacinpb.service.HealthCenterServiceImpl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class HealthCenterControllerTest {

    @Autowired
    private HealthCenterController healthCenterController;

    @MockBean
    private HealthCenterServiceImpl healthCenterServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(healthCenterController)
                .build();
    }

    @Test
    @DisplayName("Deveria cadastrar novos postos de saúde")
    public void shouldSaveANewHealthCenter() throws Exception {
        HealthCenterFormDTO healthCenterFormDTO = new HealthCenterFormDTO("2695464",
                "Centro de Saúde Padre Eustáquio",
                "Belo Horizonte",
                State.MG);

        mockMvc.perform(MockMvcRequestBuilders.post("/unidades")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(healthCenterFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Deveria retornar todos os postos de saúde")
    public void shouldListAllHealthCenter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/unidades"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria encontrar um posto de saúde pelo seu nome e retornar status OK")
    public void shouldListAHealthCenterByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/unidades?nome=PadreEustáquio"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria encontrar um posto de saúde pelo seu Estado(UF) e retornar status OK")
    public void shouldListAHealthCenterByState() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/unidades?nome=MG"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria encontrar um posto de saúde pela Cidade e retornar status OK")
    public void shouldListAHealthCenterByCity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/unidades?nome=BeloHorizonte"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria encontrar um posto de saúde pelo seu CNES e retornar status OK")
    public void shouldLisAHealthCenterByCnes() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders.get("/unidades/2695464")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria encontrar um posto de saúde pelo seu CNES, listar os funcionários e retornar status OK")
    public void shouldLisAHealthCenterByCnesAndListTheEmployee() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders.get("/unidades/2695464/funcionarios")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria atualizar os dados de um posto de saúde pelo seu CNES")
    public void shouldUpdateAnEmployeeByCpf() throws Exception {
        HealthCenterFormDTO healthCenterFormDTO = new HealthCenterFormDTO("2695464", "Centros de Saúde Padre Eustáquio", "Betim", State.MG );

        mockMvc.perform(MockMvcRequestBuilders.put("/unidades/2695464")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(healthCenterFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria deletar um posto de saúde pelo seu CNES")
    public void shouldDeleteAnEmployeeByCpf() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/unidades/2695464"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}