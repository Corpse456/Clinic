package by.gp.clinic.converter;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.SpecialityDbo;
import by.gp.clinic.dto.DoctorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorDboDtoConverter extends AbstractDboDtoConverter<DoctorDbo, DoctorDto> {

    @Override
    protected DoctorDto constructDto() {
        return new DoctorDto();
    }

    @Override
    protected DoctorDbo constructDbo() {
        return new DoctorDbo();
    }

    @Override
    protected String[] getIgnoreProperties() {
        return new String[]{"speciality"};
    }

    @Override
    protected void convertComplexFieldsForDto(final DoctorDbo sourceDbo, final DoctorDto targetDto) {
        targetDto.setSpecialityId(sourceDbo.getSpeciality().getId());
    }

    @Override
    protected void convertComplexFieldsForDbo(final DoctorDto sourceDto, final DoctorDbo targetDbo) {
        targetDbo.setSpeciality(SpecialityDbo.buildEmptyWithId(sourceDto.getSpecialityId()));
    }
}
