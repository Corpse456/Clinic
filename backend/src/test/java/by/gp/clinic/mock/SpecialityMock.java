package by.gp.clinic.mock;

import by.gp.clinic.dbo.SpecialityDbo;
import by.gp.clinic.dto.SpecialityDto;

public class SpecialityMock {

    private static final String NAME = "ENDOCRINOLOGIST";
    private static final long ID = 1L;

    public static SpecialityDto getSpecialityDtoMock() {
        final var speciality = new SpecialityDto();
        speciality.setId(ID);
        speciality.setName(NAME);
        return speciality;
    }

    public static SpecialityDbo getSpecialityDboMock() {
        final var speciality = new SpecialityDbo();
        speciality.setId(ID);
        speciality.setName(NAME);
        return speciality;
    }
}
