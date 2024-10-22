package by.gp.clinic.factory.predicateFactory;

import by.gp.clinic.search.DoctorShiftSearchRequest;
import org.springframework.stereotype.Service;

//import static by.gp.clinic.dbo.QDoctorShiftDbo.doctorShiftDbo;

@Service
public class DoctorShiftPredicateFactory extends AbstractSearchRequestPredicateFactory<DoctorShiftSearchRequest> {

    @Override
    void buildPredicates(final DoctorShiftSearchRequest searchRequest) {
//        addExpression(doctorShiftDbo.doctor.id, searchRequest.getDoctorId());
//        addExpression(doctorShiftDbo.shiftTiming.id, searchRequest.getShiftTimingId());
//        addDateExpression(doctorShiftDbo.date, searchRequest.getFrom(), searchRequest.getTo());
    }
}
