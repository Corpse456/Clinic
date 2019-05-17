package by.gp.clinic.service;

import by.gp.clinic.converter.SpecialityDboDtoConverter;
import by.gp.clinic.dbo.SpecialityDbo;
import by.gp.clinic.dto.SpecialityDto;
import by.gp.clinic.repository.SpecialityRepository;
import org.springframework.stereotype.Service;

@Service
public class SpecialityService extends AbstractService<SpecialityDbo, SpecialityDto> {

    public SpecialityService(final SpecialityDboDtoConverter converter,
                             final SpecialityRepository repository) {
        super(converter, repository);
        this.repository = repository;
    }
}
