package by.gp.clinic.mapper;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.PatientDbo;
import by.gp.clinic.dbo.TicketDbo;
import by.gp.clinic.dto.TicketDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {DoctorDbo.class, PatientDbo.class})
public interface TicketDboDtoMapper extends AbstractDboDtoMapper<TicketDbo, TicketDto> {

    @Override
    @Mapping(target = "doctor", expression = "java(DoctorDbo.buildEmptyWithId(ticketDto.getDoctorId()))")
    @Mapping(target = "patient", expression = "java(PatientDbo.buildEmptyWithId(ticketDto.getPatientId()))")
    TicketDbo mapToDbo(TicketDto ticketDto);

    @Override
    @Mapping(target = "doctorId", expression = "java(ticketDbo.getDoctor().getId())")
    @Mapping(target = "patientId", expression = "java(ticketDbo.getPatient().getId())")
    @Mapping(target = "patientName", expression = "java(ticketDbo.getPatient().getName())")
    @Mapping(target = "patientLastName", expression = "java(ticketDbo.getPatient().getLastName())")
    TicketDto mapToDto(TicketDbo ticketDbo);
}
