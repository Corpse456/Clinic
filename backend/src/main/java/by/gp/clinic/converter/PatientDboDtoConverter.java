package by.gp.clinic.converter;

import by.gp.clinic.dbo.PatientDbo;
import by.gp.clinic.dto.PatientDto;
import org.springframework.stereotype.Service;

@Service
public class PatientDboDtoConverter extends AbstractDboDtoConverter<PatientDbo, PatientDto> {

    @Override
    protected PatientDto constructDto() {
        return new PatientDto();
    }

    @Override
    protected PatientDbo constructDbo() {
        return new PatientDbo();
    }
}
