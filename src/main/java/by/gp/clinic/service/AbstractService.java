package by.gp.clinic.service;

import by.gp.clinic.converter.AbstractDboDtoConverter;
import by.gp.clinic.dbo.AbstractDbo;
import by.gp.clinic.dto.AbstractDto;
import by.gp.clinic.repository.CustomRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public class AbstractService<Dbo extends AbstractDbo, Dto extends AbstractDto> {

    private AbstractDboDtoConverter<Dbo, Dto> converter;
    CustomRepository<Dbo, Long> repository;

    @Transactional
    public Long post(final Dto dto) {
        final Dbo dbo = converter.convertToDbo(dto);
        return repository.save(dbo).getId();
    }

    @Transactional
    public Dto get(final Long id) {
        return converter.convertToDto(repository.getOne(id));
    }

    @Transactional
    public void delete(final Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public List<Dto> findAll() {
        final List<Dbo> all = repository.findAll();
        return converter.convertToDto(all);
    }

    public boolean isExists(final Long id) {
        return repository.existsById(id);
    }

    public boolean isExistsByNameAndLastName(final String name, final String lastName) {
        return repository.existsByNameAndLastName(name, lastName);
    }
}
