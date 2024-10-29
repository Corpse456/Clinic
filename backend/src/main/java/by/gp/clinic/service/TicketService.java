package by.gp.clinic.service;

import by.gp.clinic.mapper.TicketDboDtoMapper;
import by.gp.clinic.dbo.TicketDbo;
import by.gp.clinic.dto.TicketDto;
import by.gp.clinic.factory.predicateFactory.TicketPredicateFactory;
import by.gp.clinic.repository.TicketRepository;
import by.gp.clinic.search.TicketSearchRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class TicketService extends AbstractSearchService<TicketDbo, TicketDto, TicketSearchRequest> {

    private final TicketRepository repository;

    public TicketService(final TicketPredicateFactory predicateFactory,
                         final TicketDboDtoMapper mapper,
                         final TicketRepository repository) {
        super(predicateFactory, mapper, repository);
        this.repository = repository;
    }

    public int getNextNumber(final Long id, final LocalDateTime dateTime) {
        return repository.getLastTicketNumber(id, getStartDay(dateTime), getEndDay(dateTime)).orElse(0) + 1;
    }

    public boolean isTimeBusy(final Long id, final LocalDateTime dateTime) {
        return repository.getByDoctorIdAndDateTime(id, dateTime) > 0;
    }

    private static LocalDateTime getEndDay(final LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MAX);
    }

    private static LocalDateTime getStartDay(final LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MIDNIGHT);
    }
}
