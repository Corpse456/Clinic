package by.gp.clinic.service;

import by.gp.clinic.dbo.PatientDbo;
import by.gp.clinic.dto.PatientDto;
import by.gp.clinic.factory.predicateFactory.PatientPredicateFactory;
import by.gp.clinic.mapper.PatientDboDtoMapper;
import by.gp.clinic.repository.PatientRepository;
import by.gp.clinic.search.PatientSearchRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService extends AbstractSearchService<PatientDbo, PatientDto, PatientSearchRequest> {

    private final PatientRepository repository;

    public PatientService(final PatientPredicateFactory predicateFactory,
                          final PatientDboDtoMapper mapper,
                          final PatientRepository repository) {
        super(predicateFactory, mapper, repository);
        this.repository = repository;
    }

    public boolean isExistsByNameAndLastName(final String name, final String lastName) {
        return repository.existsByNameAndLastName(name, lastName);
    }

    public Optional<PatientDbo> getPatient(final String name, final String lastName) {
        return repository.getByNameAndLastName(name, lastName);
    }
}
