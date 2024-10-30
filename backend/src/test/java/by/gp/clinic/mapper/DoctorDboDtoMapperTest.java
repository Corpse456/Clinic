package by.gp.clinic.mapper;

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

public class DoctorDboDtoMapperTest extends AbstractSpringMvcTest {

    @Autowired
    private DoctorDboDtoMapper mapper;

    @Test
    public void mapToDboTest() {
        mapToDbo(DoctorMock.getDoctorDtoMock());
    }

    @Test
    public void mapToDtoTest() {
        mapToDto(DoctorMock.getDoctorDboMock());
    }

    @Test
    public void convertListToDboTest() {
        final var dtoList = DoctorMock.getListDoctorDtoMock();
        final var dboList = mapper.mapToDbo(dtoList);
        checkCollection(dtoList, dboList);
    }

    @Test
    public void convertListToDtoTest() {
        final var dboList = DoctorMock.getListDoctorDboMock();
        final var dtoList = mapper.mapToDto(dboList);
        checkCollection(dtoList, dboList);
    }

    @Test
    public void convertSetToDboTest() {
        final List<DoctorDto> dtoList = DoctorMock.getListDoctorDtoMock();
        final var dboList = mapper.mapToDbo(dtoList, Collectors.toSet());

        assertEquals(dtoList.size(), dboList.size());
    }

    @Test
    public void convertSetToSetDtoTest() {
        final List<DoctorDbo> dboList = DoctorMock.getListDoctorDboMock();
        final var dtoList = mapper.mapToDto(dboList, Collectors.toSet());

        assertEquals(dboList.size(), dtoList.size());
    }

    private void checkCollection(final Collection<DoctorDto> dtoList, final Collection<DoctorDbo> dboList) {
        final var dboIterator = dboList.iterator();
        dtoList.forEach(dto -> checkConverting(dto, dboIterator.next()));
    }

    private void mapToDbo(final DoctorDto dto) {
        final var dbo = mapper.mapToDbo(dto);
        checkConverting(dto, dbo);
    }

    private void mapToDto(final DoctorDbo dbo) {
        final var dto = mapper.mapToDto(dbo);
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
