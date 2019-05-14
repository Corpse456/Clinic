package by.gp.clinic.converter;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dto.DoctorDto;
import org.springframework.stereotype.Service;

@Service
public class DoctorDboDtoConverter extends AbstractDboDtoConverter<DoctorDbo, DoctorDto> {

    @Override
    protected DoctorDto constructDto() {
        return new DoctorDto();
    }

    @Override
    protected DoctorDbo constructDbo() {
        return new DoctorDbo();
    }
}
