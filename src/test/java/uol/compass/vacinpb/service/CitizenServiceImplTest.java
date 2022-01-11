package uol.compass.vacinpb.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uol.compass.vacinpb.dto.CitizenDTO;
import uol.compass.vacinpb.dto.CitizenVaccinesDTO;
import uol.compass.vacinpb.dto.CitizenWithVaccinesDTO;
import uol.compass.vacinpb.dto.form.CitizenFormDTO;
import uol.compass.vacinpb.dto.form.CitizenVaccinesFormDTO;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CitizenServiceImplTest {

    @Autowired
    CitizenService citizenService;

    @Test
    @DisplayName("Deve salvar um cidadão")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void saveTest() {
        CitizenFormDTO citizenForm = new CitizenFormDTO(
                "34090248086", "Fulano de Tal",
                LocalDate.of(1999, 12, 31), "000000000000000");

        CitizenDTO savedCitizen = this.citizenService.save(citizenForm);

        assertNotNull(savedCitizen);
        assertEquals(2L, savedCitizen.getId());
        assertEquals("34090248086", savedCitizen.getCpf());
    }

    @Test
    @DisplayName("Deve retornar uma lista de cidadãos")
    void getCitizensTest() {
        List<CitizenDTO> citizens = this.citizenService.getCitizens(null, null, null);

        assertEquals(1, citizens.size());
        assertEquals("53649189046", citizens.get(0).getCpf());
    }

    @Test
    @DisplayName("Deve retornar um cidadão ao procurar por um CPF cadastrado")
    void searchCitizenTest() {
        CitizenDTO citizen = this.citizenService.searchCitizen("53649189046");

        assertNotNull(citizen);
        assertEquals(1L, citizen.getId());
    }

    @Test
    @DisplayName("Deve atualizar o cidadão ao informar um CPF cadastrado")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateCitizenTest() {
        CitizenFormDTO citizenForm = new CitizenFormDTO(
                "53649189046", "Fulano de Tal",
                LocalDate.of(1999, 12, 31), "000000000000000");

        CitizenDTO citizen = this.citizenService.updateCitizen("53649189046", citizenForm);

        assertNotNull(citizen);
        assertEquals(1L, citizen.getId());
        assertEquals("Fulano de Tal", citizen.getFullName());
    }

    @Test
    @DisplayName("Deve remover cidadão ao informar um CPF cadastrado")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteCitizenTest() {
        assertEquals(1, this.citizenService.getCitizens(null, null, null).size());

        CitizenDTO citizen = this.citizenService.deleteCitizen("53649189046");

        assertEquals(1L, citizen.getId());
        assertEquals(0, this.citizenService.getCitizens(null, null, null).size());
    }

    @Test
    @DisplayName("Deve registrar uma ocorrência de vacinação para o cidadão ao informar um CPF cadastrado")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void addVaccineTest() {
        CitizenVaccinesFormDTO vaccineForm = new CitizenVaccinesFormDTO();
        LocalDate vaccinationDate = LocalDate.of(2022, 1, 1);

        vaccineForm.setVaccineId(1L);
        vaccineForm.setVaccinationDate(vaccinationDate);

        CitizenVaccinesDTO citizenVaccines = this.citizenService.addVaccine("53649189046", vaccineForm);

        assertEquals(2L, citizenVaccines.getId());
        assertEquals(vaccinationDate, citizenVaccines.getVaccinationDate());
    }

    @Test
    @DisplayName("Deve retornar um cidadão e sua lista de vacinas ao informar um CPF cadastrado")
    void listCitizenVaccinesTest() {
        CitizenWithVaccinesDTO citizen = this.citizenService.listCitizenVaccines("53649189046");

        assertEquals(1, citizen.getVaccines().size());
        assertEquals("53649189046", citizen.getCpf());
    }
}