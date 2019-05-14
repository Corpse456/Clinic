package by.gp.clinic.service;

import by.gp.clinic.converter.PatientDboDtoConverter;
import by.gp.clinic.dbo.PatientDbo;
import by.gp.clinic.dto.PatientDto;
import by.gp.clinic.repository.DoctorRepository;
import by.gp.clinic.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService extends AbstractService<PatientDbo, PatientDto> {

    private PatientRepository repository;

    public PatientService(final PatientDboDtoConverter converter,
                          final PatientRepository repository) {
        super(converter, repository);
        this.repository = repository;
    }

    public boolean isExistsByNameAndLastName(final String name, final String lastName) {
        return repository.existsByNameAndLastName(name, lastName);
    }
}
