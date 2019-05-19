package by.gp.clinic.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class PageDto<Type> {

    private long totalCount;
    private Collection<Type> elements;
}
