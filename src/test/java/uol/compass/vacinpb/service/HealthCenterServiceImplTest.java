package uol.compass.vacinpb.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uol.compass.vacinpb.dto.HealthCenterDTO;
import uol.compass.vacinpb.dto.HealthCenterEmployeesDTO;
import uol.compass.vacinpb.dto.form.HealthCenterFormDTO;
import uol.compass.vacinpb.enums.State;
import uol.compass.vacinpb.exception.ResourceNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class HealthCenterServiceImplTest {

    @Autowired
    HealthCenterService hcService;

    @Test
    @DisplayName("Deve salvar uma unidade de saúde")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void saveTest() {
        HealthCenterFormDTO hcForm = new HealthCenterFormDTO(
                "7392042", "Posto de Saúde Brás Cubas", "Rio de Janeiro", State.RJ);

        HealthCenterDTO savedHC = this.hcService.save(hcForm);

        assertNotNull(savedHC);
        assertEquals(2L, savedHC.getId());
        assertEquals("7392042", savedHC.getCnes());
    }

    @Test
    @DisplayName("Deve retornar uma lista de unidades de saúde")
    void getHealthCentersTest() {
        List<HealthCenterDTO> healthCenters = this.hcService.getHealthCenters(null, null, null);

        assertEquals(1, healthCenters.size());
        assertEquals("2695464", healthCenters.get(0).getCnes());
    }

    @Test
    @DisplayName("Deve retornar uma lista de unidades de saúde ao filtar por um nome cadastrado")
    void getHealthCentersByNameTest() {
        List<HealthCenterDTO> healthCenters = this.hcService.getHealthCenters("padre", null, null);

        assertEquals(1, healthCenters.size());
        assertTrue(healthCenters.get(0).getName().toLowerCase().contains("padre"));
    }

    @Test
    @DisplayName("Deve retornar uma lista de unidades de saúde ao filtar por Estado")
    void getHealthCentersByStateTest() {
        List<HealthCenterDTO> healthCenters = this.hcService.getHealthCenters(null, "MG", null);

        assertEquals(1, healthCenters.size());
        assertEquals(State.MG, healthCenters.get(0).getState());
    }

    @Test
    @DisplayName("Deve retornar uma lista de unidades de saúde ao filtar por cidade")
    void getHealthCentersByCityTest() {
        List<HealthCenterDTO> healthCenters = this.hcService.getHealthCenters(null, null, "belo");

        assertEquals(1, healthCenters.size());
        assertTrue(healthCenters.get(0).getCity().toLowerCase().contains("belo"));
    }

    @Test
    @DisplayName("Deve retornar uma unidade de saúde ao procurar por um CNES cadastrado")
    void searchHealthCenterTest() {
        HealthCenterDTO healthCenter = this.hcService.searchHealthCenter("2695464");

        assertNotNull(healthCenter);
        assertEquals(1L, healthCenter.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção Resource Not Found ao procurar por um CNES não cadastrado")
    void searchHealthCenterWithInvalidCnesTest() {
        assertThrows(ResourceNotFoundException.class, () -> this.hcService.searchHealthCenter("0000000"));
    }

    @Test
    @DisplayName("Deve atualizar a unidade de saúde ao informar um CNES cadastrado")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateHealthCenterTest() {
        HealthCenterFormDTO hcForm = new HealthCenterFormDTO(
                "2695464", "Unidade de Saúde do Padreco", "Belo Horizonte", State.MG);

        HealthCenterDTO healthCenter = this.hcService.updateHealthCenter("2695464", hcForm);

        assertNotNull(healthCenter);
        assertEquals(1L, healthCenter.getId());
        assertEquals("Unidade de Saúde do Padreco", healthCenter.getName());
    }

    @Test
    @DisplayName("Deve lançar exceção Resource Not Found ao tentar atualizar unidade de saúde informando um CNES não cadastrado")
    void updateHealthCenterWithInvalidCnesTest() {
        assertThrows(ResourceNotFoundException.class, () -> this.hcService.updateHealthCenter("0000000", null));
    }

    @Test
    @DisplayName("Deve remover unidade de saúde ao informar um CNES cadastrado")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteHealthCenterTest() {
        assertEquals(1, this.hcService.getHealthCenters(null, null, null).size());

        this.hcService.deleteHealthCenter("2695464");

        assertEquals(0, this.hcService.getHealthCenters(null, null, null).size());
    }

    @Test
    @DisplayName("Deve lançar exceção Resource Not Found ao tentar remover unidade de saúde informando um CNES não cadastrado")
    void deleteHealthCenterWithInvalidCnesTest() {
        assertThrows(ResourceNotFoundException.class, () -> this.hcService.deleteHealthCenter("0000000"));
    }

    @Test
    @DisplayName("Deve retornar uma unidade de saúde com a lista de seus funcionários registrados ao informar CNES cadastrado")
    void listHealthCenterEmployeesTest() {
        HealthCenterEmployeesDTO healthCenter = this.hcService.listHealthCenterEmployees("2695464");

        assertNotNull(healthCenter);
        assertEquals(1L, healthCenter.getId());
        assertEquals(0, healthCenter.getEmployees().size());
    }

    @Test
    @DisplayName("Deve lançar exceção Resource Not Found ao tentar listar funcionários registrados em uma unidade de saúde com um CNES não cadastrado")
    void listHealthCenterEmployeesWithInvalidCnesTest() {
        assertThrows(ResourceNotFoundException.class, () -> this.hcService.listHealthCenterEmployees("0000000"));
    }
}