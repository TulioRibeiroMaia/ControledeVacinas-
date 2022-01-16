package uol.compass.vacinpb.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uol.compass.vacinpb.dto.CitizenDTO;
import uol.compass.vacinpb.dto.CitizenVaccinesDTO;
import uol.compass.vacinpb.dto.CitizenWithVaccinesDTO;
import uol.compass.vacinpb.dto.form.CitizenFormDTO;
import uol.compass.vacinpb.dto.form.CitizenVaccinesFormDTO;
import uol.compass.vacinpb.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("Deve retornar uma lista com cidadãos ao filtrar por nome cadastrado")
    void getCitizensByNameTest() {
        List<CitizenDTO> citizens = this.citizenService.getCitizens("zappa", null, null);

        assertEquals(1, citizens.size());
        assertTrue(citizens.get(0).getFullName().contains("Zappa"));
    }

    @Test
    @DisplayName("Deve retornar uma lista de cidadãos ao filtrar por data de nascimento")
    void getCitizensByBirthDateTest() {
        LocalDate start = LocalDate.of(1940, 1, 1);
        LocalDate end = LocalDate.of(1940, 12, 31);

        List<CitizenDTO> citizens = this.citizenService.getCitizens(null, start, end);

        assertEquals(1, citizens.size());
        assertTrue(citizens.get(0).getBirthDate().isAfter(start));
        assertTrue(citizens.get(0).getBirthDate().isBefore(end));
    }

    @Test
    @DisplayName("Deve retornar um cidadão ao procurar por um CPF cadastrado")
    void searchCitizenTest() {
        CitizenDTO citizen = this.citizenService.searchCitizen("53649189046");

        assertNotNull(citizen);
        assertEquals(1L, citizen.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção Resource Not Found ao procurar por um CPF não cadastrado")
    void searchCitizenByInvalidCpfTest() {
        assertThrows(ResourceNotFoundException.class, () -> this.citizenService.searchCitizen("00000000000"));
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
    @DisplayName("Deve lançar exceção Resource Not Found ao tentar atualizar um CPF não cadastrado")
    void updateCitizenWithInvalidCpfTest() {
        assertThrows(ResourceNotFoundException.class, () -> this.citizenService.updateCitizen("00000000000", null));
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
    @DisplayName("Deve lançar exceção Resource Not Found ao tentar remover informando CPF não cadastrado")
    void deleteCitizenWithInvalidCpfTest() {
        assertThrows(ResourceNotFoundException.class, () -> this.citizenService.deleteCitizen("00000000000"));
    }

    @Test
    @DisplayName("Deve registrar uma ocorrência de vacinação para o cidadão ao informar um CPF cadastrado")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void addVaccineTest() {
        LocalDate vaccinationDate = LocalDate.of(2022, 1, 1);
        CitizenVaccinesFormDTO vaccineForm = new CitizenVaccinesFormDTO(1L, vaccinationDate);

        CitizenVaccinesDTO citizenVaccines = this.citizenService.addVaccine("53649189046", vaccineForm);

        assertEquals(2L, citizenVaccines.getId());
        assertEquals(vaccinationDate, citizenVaccines.getVaccinationDate());
    }

    @Test
    @DisplayName("Deve lançar exceção Resource Not Found ao tentar adicionar vacina informando CPF não cadastrado")
    void addVaccineWithInvalidCpfTest() {
        LocalDate vaccinationDate = LocalDate.of(2022, 1, 1);
        CitizenVaccinesFormDTO vaccineForm = new CitizenVaccinesFormDTO(1L, vaccinationDate);

        assertThrows(ResourceNotFoundException.class, () -> this.citizenService.addVaccine("00000000000", vaccineForm));
    }

    @Test
    @DisplayName("Deve lançar exceção Resource Not Found ao tentar adicionar vacina informando ID não cadastrado")
    void addVaccineWithInvalidIdTest() {
        LocalDate vaccinationDate = LocalDate.of(2022, 1, 1);
        CitizenVaccinesFormDTO vaccineForm = new CitizenVaccinesFormDTO(-1L, vaccinationDate);

        assertThrows(ResourceNotFoundException.class, () -> this.citizenService.addVaccine("53649189046", vaccineForm));
    }

    @Test
    @DisplayName("Deve retornar um cidadão e sua lista de vacinas ao informar um CPF cadastrado")
    void listCitizenVaccinesTest() {
        CitizenWithVaccinesDTO citizen = this.citizenService.listCitizenVaccines("53649189046");

        assertEquals(1, citizen.getVaccines().size());
        assertEquals("53649189046", citizen.getCpf());
    }
}