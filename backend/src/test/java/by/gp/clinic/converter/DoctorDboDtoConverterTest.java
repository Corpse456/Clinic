package by.gp.clinic.converter;

import by.gp.clinic.AbstractSpringMvcTest;
import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.mock.DoctorMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoctorDboDtoConverterTest extends AbstractSpringMvcTest {

    @Autowired
    private DoctorDboDtoConverter converter;

    @Test
    public void convertToDboTest() {
        convertToDbo(DoctorMock.getDoctorDtoMock());
    }

    @Test
    public void convertToDtoTest() {
        convertToDto(DoctorMock.getDoctorDboMock());
    }

    @Test
    public void convertListToDboTest() {
        final var dtoList = DoctorMock.getListDoctorDtoMock();
        final var dboList = converter.convertToDbo(dtoList);
        checkCollection(dtoList, dboList);
    }

    @Test
    public void convertListToDtoTest() {
        final var dboList = DoctorMock.getListDoctorDboMock();
        final var dtoList = converter.convertToDto(dboList);
        checkCollection(dtoList, dboList);
    }

    @Test
    public void convertSetToDboTest() {
        final List<DoctorDto> dtoList = DoctorMock.getListDoctorDtoMock();
        final var dboList = converter.convertToDbo(dtoList, Collectors.toSet());

        assertEquals(dtoList.size(), dboList.size());
    }

    @Test
    public void convertSetToSetDtoTest() {
        final List<DoctorDbo> dboList = DoctorMock.getListDoctorDboMock();
        final var dtoList = converter.convertToDto(dboList, Collectors.toSet());

        assertEquals(dboList.size(), dtoList.size());
    }

    private void checkCollection(final Collection<DoctorDto> dtoList, final Collection<DoctorDbo> dboList) {
        final var dboIterator = dboList.iterator();
        dtoList.forEach(dto -> checkConverting(dto, dboIterator.next()));
    }

    private void convertToDbo(final DoctorDto dto) {
        final var dbo = converter.convertToDbo(dto);
        checkConverting(dto, dbo);
    }

    private void convertToDto(final DoctorDbo dbo) {
        final var dto = converter.convertToDto(dbo);
        checkConverting(dto, dbo);
    }

    private void checkConverting(final DoctorDto dto, final DoctorDbo dbo) {
        assertEquals(dto.getId(), dbo.getId());
        assertEquals(dto.getBirthDate(), dbo.getBirthDate());
        assertEquals(dto.getGender(), dbo.getGender());
        assertEquals(dto.getName(), dbo.getName());
        assertEquals(dto.getLastName(), dbo.getLastName());
    }
}
