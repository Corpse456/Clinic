package by.gp.clinic.mapper;

import by.gp.clinic.dbo.TicketDbo;
import by.gp.clinic.dto.TicketDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketDboDtoMapper extends AbstractDboDtoMapper<TicketDbo, TicketDto> {
}
