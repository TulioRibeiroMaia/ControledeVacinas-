package uol.compass.vacinpb.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uol.compass.vacinpb.dto.VaccineDTO;
import uol.compass.vacinpb.dto.form.VaccineFormDTO;
import uol.compass.vacinpb.enums.VaccineName;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class VaccineServiceImplTest {

    @Autowired
    VaccineService vaccineService;

    @Test
    @DisplayName("Deve salvar uma vacina")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void saveTest() {
        VaccineFormDTO vaccineForm = new VaccineFormDTO(
                VaccineName.PFIZER, "BioNTech", LocalDate.of(2021, 12, 15),
                LocalDate.of(2022, 6, 19), "PFZ96R1");

        VaccineDTO savedVaccine = this.vaccineService.save(vaccineForm);

        assertNotNull(savedVaccine);
        assertEquals(2L, savedVaccine.getId());
        assertEquals(VaccineName.PFIZER, savedVaccine.getVaccineName());
    }

    @Test
    @DisplayName("Deve retornar uma lista de vacinas")
    void getVaccinesTest() {
        List<VaccineDTO> vaccines = this.vaccineService.getVaccines(null, null);

        assertEquals(1, vaccines.size());
        assertEquals(VaccineName.BCG, vaccines.get(0).getVaccineName());
    }

    @Test
    @DisplayName("Deve retornar uma vacina ao procurar por um ID cadastrado")
    void searchVaccineTest() {
        VaccineDTO vaccine = this.vaccineService.searchVaccine(1L);

        assertNotNull(vaccine);
        assertEquals(1L, vaccine.getId());
        assertEquals(VaccineName.BCG, vaccine.getVaccineName());
    }

    @Test
    @DisplayName("Deve atualizar a vacina ao informar um ID cadastrado")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateVaccineTest() {
        VaccineFormDTO vaccineForm = new VaccineFormDTO(
                VaccineName.PFIZER, "BioNTech", LocalDate.of(2021, 12, 15),
                LocalDate.of(2022, 6, 19), "PFZ96R1");

        VaccineDTO vaccine = this.vaccineService.updateVaccine(1L, vaccineForm);

        assertNotNull(vaccine);
        assertEquals(1L, vaccine.getId());
        assertEquals(VaccineName.PFIZER, vaccine.getVaccineName());
    }

    @Test
    @DisplayName("Deve remover cidadão ao informar um ID cadastrado")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteVaccineTest() {
        assertEquals(1, this.vaccineService.getVaccines(null, null).size());

        VaccineDTO vaccine = this.vaccineService.deleteVaccine(1L);

        assertEquals(1L, vaccine.getId());
        assertEquals(0, this.vaccineService.getVaccines(null, null).size());
    }
}