package by.gp.clinic.service;

import by.gp.clinic.converter.UserDboDtoConverter;
import by.gp.clinic.dbo.UserDbo;
import by.gp.clinic.dto.UserDto;
import by.gp.clinic.factory.predicateFactory.UserPredicateFactory;
import by.gp.clinic.repository.UserRepository;
import by.gp.clinic.search.UserSearchRequest;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserService extends AbstractSearchService<UserDbo, UserDto, UserSearchRequest> {

    private final UserRepository repository;

    private final Cache<Long, UserDto> cache = CacheBuilder
        .newBuilder()
        .expireAfterWrite(120, TimeUnit.MINUTES)
        .build();

    public UserService(final UserPredicateFactory predicateFactory,
                       final UserDboDtoConverter converter,
                       final UserRepository repository) {
        super(predicateFactory, converter, repository);
        this.repository = repository;
    }

    @Override
    public UserDbo save(final UserDbo dbo) {
        final var savedUser = super.save(dbo);
        cache.put(savedUser.getId(), convertToDto(savedUser));
        return savedUser;
    }

    @Override
    public UserDto get(final Long id) {
        return getFromCache(id).orElseGet(() -> {
            final var userDto = super.get(id);
            cache.put(id, userDto);
            return userDto;
        });
    }

    private Optional<UserDto> getFromCache(final Long id) {
        return Optional.ofNullable(cache.getIfPresent(id));
    }

    public boolean isExists(final Long id) {
        final var user = cache.getIfPresent(id);
        if (user != null) {
            return true;
        }
        return super.isExists(id);
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
