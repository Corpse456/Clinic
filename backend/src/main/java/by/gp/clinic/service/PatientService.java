package by.gp.clinic.service;

import by.gp.clinic.converter.PatientDboDtoConverter;
import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.PatientDbo;
import by.gp.clinic.dto.PatientDto;
import by.gp.clinic.repository.PatientRepository;
import by.gp.clinic.search.PatientSearchRequest;
import by.gp.clinic.factory.predicateFactory.PatientPredicateFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService extends AbstractSearchService<PatientDbo, PatientDto, PatientSearchRequest> {

    private PatientRepository repository;

    public PatientService(final PatientPredicateFactory predicateFactory,
                          final PatientDboDtoConverter converter,
                          final PatientRepository repository) {
        super(predicateFactory, converter, repository);
        this.repository = repository;
    }

    public boolean isExistsByNameAndLastName(final String name, final String lastName) {
        return repository.existsByNameAndLastName(name, lastName);
    }

    public Optional<PatientDbo> getPatient(final String name, final String lastName) {
        return repository.getByNameAndLastName(name, lastName);
    }
}
