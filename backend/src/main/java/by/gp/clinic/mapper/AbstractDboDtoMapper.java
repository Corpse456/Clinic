package by.gp.clinic.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public interface AbstractDboDtoMapper<Dbo, Dto> {

    Dbo mapToDbo(Dto dto);

    Dto mapToDto(Dbo dbo);

    List<Dto> mapToDto(List<Dbo> all);

    List<Dbo> mapToDbo(List<Dto> all);

    default <CollectionType extends Collection<Dbo>> CollectionType mapToDbo(
            final Collection<Dto> collection,
            final Collector<Dbo, ?, CollectionType> collector) {
        return collection.stream().map(this::mapToDbo).collect(collector);
    }

    default List<Dbo> mapToDbo(final Collection<Dto> collection) {
        return mapToDbo(collection, Collectors.toList());
    }

    default <CollectionType extends Collection<Dto>> CollectionType mapToDto(
            final Collection<Dbo> collection,
            final Collector<Dto, ?, CollectionType> collector) {
        return collection.stream().map(this::mapToDto).collect(collector);
    }

    default List<Dto> mapToDto(final Collection<Dbo> collection) {
        return mapToDto(collection, Collectors.toList());
    }

}
