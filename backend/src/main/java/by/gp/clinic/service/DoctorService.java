package by.gp.clinic.service;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.factory.predicateFactory.DoctorPredicateFactory;
import by.gp.clinic.mapper.DoctorDboDtoMapper;
import by.gp.clinic.repository.DoctorRepository;
import by.gp.clinic.search.DoctorSearchRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService extends AbstractSearchService<DoctorDbo, DoctorDto, DoctorSearchRequest> {

    private final DoctorRepository repository;

    public DoctorService(final DoctorPredicateFactory predicateFactory,
                         final DoctorDboDtoMapper mapper,
                         final DoctorRepository repository) {
        super(predicateFactory, mapper, repository);
        this.repository = repository;
    }

    public boolean isExistsByNameAndLastName(final String name, final String lastName) {
        return repository.existsByNameAndLastName(name, lastName);
    }

    public Optional<DoctorDbo> getDoctor(final String name, final String lastName, final String specialIdentifier) {
        return repository.getByNameAndLastNameAndSpecialIdentifier(name, lastName, specialIdentifier);
    }
}
