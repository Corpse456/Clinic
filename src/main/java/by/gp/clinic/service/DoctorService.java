package by.gp.clinic.service;

import by.gp.clinic.converter.DoctorDboDtoConverter;
import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.repository.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorService extends AbstractService<DoctorDbo, DoctorDto> {

    public DoctorService(final DoctorDboDtoConverter converter,
                         final DoctorRepository repository) {
        super(converter, repository);
        this.repository = repository;
    }
}
