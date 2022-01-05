package uol.compass.vacinpb.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VaccineName {
    BCG("bcg"),
    HEPATITE_B("hepatite b"),
    PENTAVALENTE("pentavalente"),
    POLIOMELITE("poliomelite"),
    PNEUMOCÓCICA("pneumocócica"),
    ROTAVÍRUS("rotavírus"),
    MENINGOCÓCICA_C("meningocócica c"),
    INFLUENZA("influenza"),
    FEBRE_AMARELA("febre amarela"),
    TRIPLICE_VIRAL("triplice viral"),
    DTP("dtp"),
    HEPATITE_A("hepatite a"),
    TETRA_VIRAL("tetra viral"),
    VARICELA_ATENUADA("varicela atenuada"),
    HPV("hpv"),
    DT("dt"),
    DTPA("dtpa"),
    PFIZER("pfizer"),
    CORONAVAC("coronavac"),
    JANSSEN("janssen"),
    ASTRAZENECA("astrazeneca");

    private String value;

    VaccineName(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static VaccineName forValues(@JsonProperty("nome vacina") String value) {
        for (VaccineName vaccineName : VaccineName.values()) {
            if (vaccineName.value.equalsIgnoreCase(value)) {
                return vaccineName;
            }
        }
        return null;
    }
}
