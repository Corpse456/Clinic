package by.gp.clinic.service;

import by.gp.clinic.converter.DoctorDboDtoConverter;
import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.SpecialityDbo;
import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.repository.DoctorRepository;
import by.gp.clinic.search.DoctorSearchRequest;
import by.gp.clinic.factory.predicateFactory.DoctorPredicateFactory;
import org.springframework.stereotype.Service;

@Service
public class DoctorService extends AbstractSearchService<DoctorDbo, DoctorDto, DoctorSearchRequest> {

    private DoctorRepository repository;

    public DoctorService(final DoctorPredicateFactory predicateFactory,
                         final DoctorDboDtoConverter converter,
                         final DoctorRepository repository) {
        super(predicateFactory, converter, repository);
        this.repository = repository;
    }

    public boolean isExistsByNameAndLastName(final String name, final String lastName) {
        return repository.existsByNameAndLastName(name, lastName);
    }

    public SpecialityDbo getSpeciality(final Long id) {
        return repository.getSpecialityById(id);
    }
}
