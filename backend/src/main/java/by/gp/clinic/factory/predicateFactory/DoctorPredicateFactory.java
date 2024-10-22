package by.gp.clinic.factory.predicateFactory;

import by.gp.clinic.search.DoctorSearchRequest;
import org.springframework.stereotype.Service;

import static by.gp.clinic.dbo.QDoctorDbo.doctorDbo;

@Service
public class DoctorPredicateFactory extends AbstractSearchRequestPredicateFactory<DoctorSearchRequest> {

    @Override
    void buildPredicates(final DoctorSearchRequest searchRequest) {
        addExpression(doctorDbo.name, searchRequest.getName());
        addExpression(doctorDbo.lastName, searchRequest.getLastName());
        addExpression(doctorDbo.gender, searchRequest.getGender());
        addExpression(doctorDbo.speciality.id, searchRequest.getSpecialityId());
    }
}
