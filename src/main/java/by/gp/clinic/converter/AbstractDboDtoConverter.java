package by.gp.clinic.converter;

import org.springframework.beans.BeanUtils;

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
