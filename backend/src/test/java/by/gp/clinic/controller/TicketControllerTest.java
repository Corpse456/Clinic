package by.gp.clinic.controller;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.PatientDbo;
import by.gp.clinic.dto.PageDto;
import by.gp.clinic.dto.TicketDto;
import by.gp.clinic.repository.DoctorRepository;
import by.gp.clinic.repository.PatientRepository;
import by.gp.clinic.repository.TicketRepository;
import by.gp.clinic.search.TicketSearchRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.List;

import static by.gp.clinic.mock.TicketMock.getTicketDtoMock;
import static java.time.LocalDateTime.now;

public class TicketControllerTest extends AbstractControllerTest {

    private static final String TICKET_URL = "/ticket";

    @Autowired
    private TicketRepository repository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void createTicketTest() {
        addEntity();
    }

    @Test
    public void addTicketInPastTest() {
        final TicketDto ticketDtoMock = getDtoMock();
        ticketDtoMock.setDateTime(LocalDateTime.now().minusHours(1).withMinute(15).withSecond(0));
        addEntityWithStatus(ticketDtoMock, 400, "Date must be in future");
    }

    @Test
    public void addTicketWrongTimeTest() {
        final TicketDto ticketDtoMock = getDtoMock();
        ticketDtoMock.setDateTime(ticketDtoMock.getDateTime().withMinute(13));
        addEntityWithStatus(ticketDtoMock, 400, "Ticket date must be a multiple of 15");
    }

    @Test
    public void searchTicketTest() {
        final List<PatientDbo> patients = patientRepository.findAll();
        final List<DoctorDbo> doctors = doctorRepository.findAll();

        patients.forEach(p -> {
            for (int i = 0; i < 10; i++) {
                final TicketDto ticketDto = new TicketDto();
                ticketDto.setDoctorId(doctors.get((int) (doctors.size() * Math.random())).getId());
                ticketDto.setPatientId(p.getId());
                ticketDto.setDateTime(now().plusHours((long) (Math.random() * 200) + 2).withMinute(15).withSecond(0));
                postQuery(getUrl(), ticketDto);
            }
        });

        findEntitiesTest(new TicketSearchRequest(), new TypeReference<PageDto<TicketDto>>() {
        });
    }

    @Override
    protected String getUrl() {
        return TICKET_URL;
    }

    @Override
    protected TicketRepository getRepository() {
        return repository;
    }

    @Override
    protected TicketDto getDtoMock() {
        return getTicketDtoMock();
    }
}