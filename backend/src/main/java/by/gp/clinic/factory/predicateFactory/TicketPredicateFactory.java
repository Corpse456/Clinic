package by.gp.clinic.factory.predicateFactory;

import by.gp.clinic.search.TicketSearchRequest;
import org.springframework.stereotype.Service;

import static by.gp.clinic.dbo.QTicketDbo.ticketDbo;

@Service
public class TicketPredicateFactory extends AbstractSearchRequestPredicateFactory<TicketSearchRequest> {

    @Override
    void buildPredicates(final TicketSearchRequest searchRequest) {
        addExpression(ticketDbo.doctor.id, searchRequest.getDoctorId());
        addExpression(ticketDbo.doctor.id, searchRequest.getPatientId());
        addDateTimeExpression(ticketDbo.dateTime, searchRequest.getFrom(), searchRequest.getTo());
    }
}
