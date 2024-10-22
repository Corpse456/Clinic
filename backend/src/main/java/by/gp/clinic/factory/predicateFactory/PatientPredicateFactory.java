package by.gp.clinic.factory.predicateFactory;

import by.gp.clinic.search.PatientSearchRequest;
import org.springframework.stereotype.Service;

//import static by.gp.clinic.dbo.QPatientDbo.patientDbo;

@Service
public class PatientPredicateFactory extends AbstractSearchRequestPredicateFactory<PatientSearchRequest> {

    @Override
    void buildPredicates(final PatientSearchRequest searchRequest) {
//        addExpression(patientDbo.name, searchRequest.getName());
//        addExpression(patientDbo.lastName, searchRequest.getLastName());
//        addExpression(patientDbo.gender, searchRequest.getGender());
    }
}
