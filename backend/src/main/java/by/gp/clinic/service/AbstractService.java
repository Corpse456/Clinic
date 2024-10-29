package by.gp.clinic.service;

import by.gp.clinic.dbo.AbstractDbo;
import by.gp.clinic.dto.AbstractDto;
import by.gp.clinic.mapper.AbstractDboDtoMapper;
import by.gp.clinic.repository.CustomRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public class AbstractService<Dbo extends AbstractDbo, Dto extends AbstractDto> {

    AbstractDboDtoMapper<Dbo, Dto> mapper;
    CustomRepository<Dbo, Long> repository;

    @Transactional
    public Dbo post(final Dto dto) {
        final var dbo = mapper.mapToDbo(dto);
        return save(dbo);
    }

    @Transactional
    public Dbo save(final Dbo dbo) {
        return repository.save(dbo);
    }

    @Transactional
    public Dto get(final Long id) {
        return mapper.mapToDto(repository.getReferenceById(id));
    }

    @Transactional
    @SuppressWarnings("unused")
    public Dbo getDbo(final Long id) {
        return repository.getReferenceById(id);
    }

    @SuppressWarnings("unused")
    public Dbo mapToDbo(final Dto dto) {
        return mapper.mapToDbo(dto);
    }

    public Dto mapToDto(final Dbo dbo) {
        return mapper.mapToDto(dbo);
    }

    @Transactional
    public void delete(final Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @SuppressWarnings("unused")
    public List<Dto> findAll() {
        final var all = repository.findAll();
        return mapper.mapToDto(all);
    }

    @Transactional
    public List<Dbo> findAllDbo() {
        return repository.findAll();
    }

    public boolean isExists(final Long id) {
        return repository.existsById(id);
    }
}
