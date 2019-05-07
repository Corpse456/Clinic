package by.gp.clinic.service;

import by.gp.clinic.converter.TicketDboDtoConverter;
import by.gp.clinic.dbo.TicketDbo;
import by.gp.clinic.dto.TicketDto;
import by.gp.clinic.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class TicketService extends AbstractService<TicketDbo, TicketDto> {

    private final TicketRepository repository;

    public TicketService(final TicketDboDtoConverter converter,
                         final TicketRepository repository) {
        super(converter, repository);
        this.repository = repository;
    }

    public int getNextNumber(final Long id, final LocalDateTime dateTime) {
        return repository.getLastTicketNumber(id, getStartDay(dateTime), getEndDay(dateTime)).orElse(1);
    }

    private static LocalDateTime getEndDay(final LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.toLocalDate().plusDays(1), LocalTime.MIDNIGHT).minusSeconds(1);
    }

    private static LocalDateTime getStartDay(final LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MIDNIGHT);
    }

    public static void main(String[] args) {
        System.out.println(getStartDay(LocalDateTime.now()));
        System.out.println(getEndDay(LocalDateTime.now()));
    }
}
