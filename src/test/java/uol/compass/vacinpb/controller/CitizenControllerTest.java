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
import uol.compass.vacinpb.dto.form.CitizenFormDTO;
import uol.compass.vacinpb.dto.form.CitizenVaccinesFormDTO;
import uol.compass.vacinpb.service.CitizenServiceImpl;


import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CitizenControllerTest {

    @Autowired
    private CitizenController citizenController;

    @MockBean
    private CitizenServiceImpl citizenServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(citizenController)
                .build();
    }

    @Test
    @DisplayName("Deveria cadastrar um novo cidadão")
    public void shouldSaveANewCitizen() throws Exception {
        CitizenFormDTO citizenFormDTO = new CitizenFormDTO("40040998037", "Francisco Vicente Zappa", LocalDate.now(), "318596810001714");

        mockMvc.perform(MockMvcRequestBuilders.post("/cidadaos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(citizenFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Deveria retornar Bad Request ao tentar salvar usuário com campo inválido")
    public void saveCitizenWithInvalidField() throws Exception {
        // cpf inválido
        CitizenFormDTO citizenFormDTO = new CitizenFormDTO(
                "00000000000", "Francisco Vicente Zappa", LocalDate.now(), "318596810001714");

        mockMvc.perform(MockMvcRequestBuilders.post("/cidadaos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(citizenFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Deveria retornar Bad Request ao tentar salvar usuário com campo vazio")
    public void saveCitizenWithEmptyField() throws Exception {
        // nome vazio
        CitizenFormDTO citizenFormDTO = new CitizenFormDTO(
                "40040998037", "", LocalDate.now(), "318596810001714");

        mockMvc.perform(MockMvcRequestBuilders.post("/cidadaos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(citizenFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Deveria cadastrar vacinas em um cidadão")
    public void shouldSaveVaccinesInACitizen() throws Exception {
        CitizenVaccinesFormDTO citizenVaccinesFormDTO = new CitizenVaccinesFormDTO(1L, LocalDate.now());

        mockMvc.perform(MockMvcRequestBuilders.post("/cidadaos/40040998037/vacinas")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(citizenVaccinesFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria retornar todos cidadãos cadastrados")
    public void shouldListAllCitizens() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cidadaos"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria encontrar um cidadão pelo seu nome e retornar status OK")
    public void shouldListACitizenByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cidadaos?nome=pedro"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria encontrar um cidadão por sua idade e retornar status OK")
    public void shouldLisACitizenByAge() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cidadaos?data-inicial=31121990&data-final=10012022"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria encontrar um cidadão pelo seu CPF e retornar status OK")
    public void shouldListACitizenByCpf() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders.get("/cidadaos/40040998037")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria encontrar um cidadão pelo seu CPF e mostrar suas vacinas e retornar status OK")
    public void shouldLisACitizenByCpfAndShowTheirVaccines() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders.get("/cidadaos/40040998037/vacinas")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria atualizar os dados de um cidadão pelo seu CPF")
    public void shouldUpdateACitizenByCpf() throws Exception {
        CitizenFormDTO citizenFormDTO = new CitizenFormDTO("40040998037", "Francisco Vicente Zappas", LocalDate.now(), "318596810001714");

        mockMvc.perform(MockMvcRequestBuilders.put("/cidadaos/40040998037")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(citizenFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria deletar um cidadão pelo seu CPF")
    public void shouldDeleteACitizenByTheirCpf() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/cidadaos/40040998037"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

//Testes para exceções
//    @Test
//    @DisplayName("Retorna BadRequest quando não encontra um cidadão pelo nome")
//    void whenNotGetCitizenByNameThenReturnsStatusBadRequest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/cidadaos/Jurandir"))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest());
//    }
//    @Test
//    @DisplayName("Retorna BadRequest quando não encontra um cidadão pelo CPF")
//    void whenNotSearchCitizenByCpfThenReturnStatusBadRequest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/cidadaos/00000000000"))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest());
//    }

