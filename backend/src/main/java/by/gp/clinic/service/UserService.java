package by.gp.clinic.service;

import by.gp.clinic.converter.UserDboDtoConverter;
import by.gp.clinic.dbo.UserDbo;
import by.gp.clinic.dto.UserDto;
import by.gp.clinic.factory.predicateFactory.UserPredicateFactory;
import by.gp.clinic.repository.UserRepository;
import by.gp.clinic.search.UserSearchRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractSearchService<UserDbo, UserDto, UserSearchRequest> {

    private final UserRepository repository;

    public UserService(final UserPredicateFactory predicateFactory,
                       final UserDboDtoConverter converter,
                       final UserRepository repository) {
        super(predicateFactory, converter, repository);
        this.repository = repository;
    }

    public boolean existsByAlias(final String alias) {
        return repository.existsByAlias(alias);
    }

    public boolean existsByDoctorId(final Long id) {
        return repository.existsByDoctorId(id);
    }

    public boolean existsByPatientId(final Long id) {
        return repository.existsByPatientId(id);
    }
}
