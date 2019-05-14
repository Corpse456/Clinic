package by.gp.clinic.converter;

import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class AbstractDboDtoConverter<Dbo, Dto> {

    private String[] ignoreProperties;

    public AbstractDboDtoConverter() {
        ignoreProperties = getIgnoreProperties();
    }

    public Dto convertToDto(final Dbo dbo) {
        if (dbo == null) {
            return null;
        }

        final Dto dto = constructDto();

        BeanUtils.copyProperties(dbo, dto, ignoreProperties);

        convertComplexFieldsForDto(dbo, dto);
        return dto;
    }

    public Dbo convertToDbo(final Dto dto) {
        if (dto == null) {
            return null;
        }

        final Dbo dbo = constructDbo();

        BeanUtils.copyProperties(dto, dbo, ignoreProperties);

        convertComplexFieldsForDbo(dto, dbo);
        return dbo;
    }

    public <CollectionType extends Collection<Dbo>>
    CollectionType convertToDbo(final Collection<Dto> collection, final Collector<Dbo, ?, CollectionType> collector) {
        return collection.stream().map(this::convertToDbo).collect(collector);
    }

    public List<Dbo> convertToDbo(final Collection<Dto> collection) {
        return convertToDbo(collection, Collectors.toList());
    }

    public <CollectionType extends Collection<Dto>>
    CollectionType convertToDto(final Collection<Dbo> collection, final Collector<Dto, ?, CollectionType> collector) {
        return collection.stream().map(this::convertToDto).collect(collector);
    }

    public List<Dto> convertToDto(final Collection<Dbo> collection) {
        return convertToDto(collection, Collectors.toList());
    }

    protected abstract Dto constructDto();

    protected abstract Dbo constructDbo();

    protected void convertComplexFieldsForDto(final Dbo sourceDbo, final Dto targetDto) {
    }

    protected void convertComplexFieldsForDbo(final Dto sourceDto, final Dbo targetDbo) {
    }

    protected String[] getIgnoreProperties() {
        return null;
    }

    protected void setIgnoreProperties(final String[] newProperties) {
        ignoreProperties = newProperties;
    }
}
