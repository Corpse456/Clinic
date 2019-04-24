package by.gp.clinic.service;

import by.gp.clinic.converter.AbstractDboDtoConverter;
import by.gp.clinic.dbo.AbstractDbo;
import by.gp.clinic.dto.AbstractDto;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class AbstractService<Dbo extends AbstractDbo, Dto extends AbstractDto> {

    private AbstractDboDtoConverter<Dbo, Dto> converter;
    JpaRepository<Dbo, Long> repository;

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

    public boolean isExists(final Long id) {
        return repository.existsById(id);
    }
}
