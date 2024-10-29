package by.gp.clinic.service;

import by.gp.clinic.converter.SpecialityDboDtoConverter;
import by.gp.clinic.dbo.SpecialityDbo;
import by.gp.clinic.dto.SpecialityDto;
import by.gp.clinic.repository.SpecialityRepository;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableCollection;
import io.jsonwebtoken.lang.Collections;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SpecialityService extends AbstractService<SpecialityDbo, SpecialityDto> {

    private final Cache<Long, SpecialityDto> cache = CacheBuilder
        .newBuilder()
        .expireAfterWrite(120, TimeUnit.MINUTES)
        .build();

    public SpecialityService(final SpecialityDboDtoConverter converter,
                             final SpecialityRepository repository) {
        super(converter, repository);
        this.repository = repository;
    }

    @Override
    public List<SpecialityDto> findAll() {
        final var allFromCache = getAllFromCache();
        if (!Collections.isEmpty(allFromCache)) {
            return new ArrayList<>(allFromCache);
        }

        final var all = super.findAll();
        all.forEach(speciality -> cache.put(speciality.getId(), speciality));
        return all;
    }

    private ImmutableCollection<SpecialityDto> getAllFromCache() {
        return cache.getAllPresent(cache.asMap().keySet()).values();
    }
}
