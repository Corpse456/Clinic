package by.gp.clinic.service;

import by.gp.clinic.converter.DoctorDboDtoConverter;
import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorDboDtoConverter converter;
    private final DoctorRepository repository;

    @Transactional
    public Long hireDoctor(final DoctorDto doctor) {
        final DoctorDbo doctorDbo = converter.convertToDbo(doctor);
        return repository.save(doctorDbo).getId();
    }

    @Transactional
    public void fireDoctor(final Long id) {
        repository.deleteById(id);
    }

    public boolean isDoctorExists(final String name, final String lastName) {
        return repository.existsByNameAndLastName(name, lastName);
    }

    public boolean isDoctorExists(final Long id) {
        return repository.existsById(id);
    }

}
