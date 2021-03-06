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

    AbstractDboDtoConverter<Dbo, Dto> converter;
    CustomRepository<Dbo, Long> repository;

    @Transactional
    public Dbo post(final Dto dto) {
        final Dbo dbo = converter.convertToDbo(dto);
        return save(dbo);
    }

    @Transactional
    public Dbo save(final Dbo dbo) {
        return repository.save(dbo);
    }

    @Transactional
    public Dto get(final Long id) {
        return converter.convertToDto(repository.getOne(id));
    }

    @Transactional
    public Dbo getDbo(final Long id) {
        return repository.getOne(id);
    }

    public Dbo convertToDbo(final Dto dto) {
        return converter.convertToDbo(dto);
    }

    public Dto convertToDto(final Dbo dbo) {
        return converter.convertToDto(dbo);
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

    @Transactional
    public List<Dbo> findAllDbo() {
        return repository.findAll();
    }

    public boolean isExists(final Long id) {
        return repository.existsById(id);
    }
}
