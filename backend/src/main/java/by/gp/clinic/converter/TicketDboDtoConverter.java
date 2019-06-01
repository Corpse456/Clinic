package by.gp.clinic.converter;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.PatientDbo;
import by.gp.clinic.dbo.TicketDbo;
import by.gp.clinic.dto.TicketDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketDboDtoConverter extends AbstractDboDtoConverter<TicketDbo, TicketDto> {

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
        targetDbo.setDoctor(DoctorDbo.buildEmptyWithId(sourceDto.getDoctorId()));
        targetDbo.setPatient(PatientDbo.buildEmptyWithId(sourceDto.getPatientId()));
    }

    @Override
    protected void convertComplexFieldsForDto(final TicketDbo sourceDbo, final TicketDto targetDto) {
        targetDto.setDoctorId(sourceDbo.getDoctor().getId());
        targetDto.setPatientId(sourceDbo.getPatient().getId());
        targetDto.setPatientName(sourceDbo.getPatient().getName());
        targetDto.setPatientLastName(sourceDbo.getPatient().getLastName());
    }
}
