package by.gp.clinic.factory.predicateFactory;

import by.gp.clinic.search.PageableSearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static liquibase.util.StringUtils.isNotEmpty;

public abstract class AbstractSearchRequestPredicateFactory<T extends PageableSearchRequest> {

    private BooleanBuilder booleanBuilder;

    public Optional<Predicate> build(T searchRequest) {
        booleanBuilder = new BooleanBuilder();
        buildPredicates(searchRequest);
        return Optional.ofNullable(booleanBuilder.getValue());
    }

    abstract void buildPredicates(final T searchRequest);

    void addExpression(final StringPath field, final String value) {
        if (isNotEmpty(value)) {
            booleanBuilder.and(field.containsIgnoreCase(value));
        }
    }

    <E extends Enum<E>> void addExpression(final EnumPath<E> field, final E value) {
        if (value != null) {
            booleanBuilder.and(field.eq(value));
        }
    }

    <N extends Number & Comparable<?>> void addExpression(final NumberPath<N> field, final N value) {
        if (value != null) {
            booleanBuilder.and(field.eq(value));
        }
    }

    void addDateExpression(final DatePath<LocalDate> date, final LocalDate from, final LocalDate to) {
        if (from != null) {
            booleanBuilder.and(date.after(from.minusDays(1)));
        }
        if (to != null) {
            booleanBuilder.and(date.before(to.plusDays(1)));
        }
    }

    void addDateTimeExpression(final DateTimePath<LocalDateTime> date,
                               final LocalDateTime from,
                               final LocalDateTime to) {
        if (from != null) {
            booleanBuilder.and(date.after(from.minusSeconds(1)));
        }
        if (to != null) {
            booleanBuilder.and(date.before(to.plusSeconds(1)));
        }
    }
}
