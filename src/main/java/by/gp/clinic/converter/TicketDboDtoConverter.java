package by.gp.clinic.converter;

import by.gp.clinic.dbo.TicketDbo;
import by.gp.clinic.dto.TicketDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketDboDtoConverter extends AbstractDboDtoConverter<TicketDbo, TicketDto> {

    private final DoctorDboDtoConverter doctorConverter;
    private final PatientDboDtoConverter patientConverter;

    @Override
    protected TicketDto constructDto() {
        return new TicketDto();
    }

    @Override
    protected TicketDbo constructDbo() {
        return new TicketDbo();
    }

    @Override
    protected void convertComplexFieldsForDbo(final TicketDto sourceDto, final TicketDbo targetDbo) {
        targetDbo.setDoctor(doctorConverter.convertToDbo(sourceDto.getDoctor()));
        targetDbo.setPatient(patientConverter.convertToDbo(sourceDto.getPatient()));
    }

    @Override
    protected void convertComplexFieldsForDto(final TicketDbo sourceDbo, final TicketDto targetDto) {
        targetDto.setDoctor(doctorConverter.convertToDto(sourceDbo.getDoctor()));
        targetDto.setPatient(patientConverter.convertToDto(sourceDbo.getPatient()));
    }
}
