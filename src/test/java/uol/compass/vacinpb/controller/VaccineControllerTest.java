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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uol.compass.vacinpb.dto.form.VaccineFormDTO;
import uol.compass.vacinpb.enums.VaccineName;
import uol.compass.vacinpb.service.VaccineServiceImpl;
import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class VaccineControllerTest {

    @Autowired
    private VaccineController vaccineController;

    @MockBean
    private VaccineServiceImpl vaccineServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(vaccineController)
                .build();
    }

    @Test
    @DisplayName("Deveria cadastrar novas vacinas")
    public void shouldSaveANewVaccine() throws Exception {
        VaccineFormDTO vaccineFormDTO = new VaccineFormDTO( VaccineName.PFIZER,
                "BioNTech",
                LocalDate.now(),
                LocalDate.now(),
                "PFZ96R1");

        mockMvc.perform(MockMvcRequestBuilders.post("/vacinas")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(vaccineFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Deveria retornar todas vacinas")
    public void shouldListAllVaccines() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vacinas"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria encontrar uma vacina pelo seu nome e retornar status OK")
    public void shouldListAVaccineByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vacinas?nome=BCG"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria encontrar uma vacina pelo seu lote(número) e retornar status OK")
    public void shouldListAVaccineByLotNumber() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vacinas?lote=PFZ96R1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria encontrar uma vacina pelo seu ID e retornar status OK")
    public void shouldListAVaccineById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vacinas/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria atualizar os dados de uma vacina pelo seu ID")
    public void shouldUpdateAVaccineById() throws Exception {
        VaccineFormDTO vaccineFormDTO = new VaccineFormDTO(VaccineName.PFIZER,
                "BioNTech",
                LocalDate.now(),
                LocalDate.now(),
                "PFZ96R3");

        mockMvc.perform(MockMvcRequestBuilders.put("/vacinas/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(vaccineFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria deletar uma vacina pelo seu ID")
    public void shouldDeleteAVaccineById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/vacinas/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}