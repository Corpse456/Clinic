package by.gp.clinic.service;

import by.gp.clinic.converter.AbstractDboDtoConverter;
import by.gp.clinic.dbo.AbstractDbo;
import by.gp.clinic.dto.AbstractDto;
import by.gp.clinic.dto.PageDto;
import by.gp.clinic.repository.CustomRepository;
import by.gp.clinic.search.OffsetLimitRequest;
import by.gp.clinic.search.PageableSearchRequest;
import by.gp.clinic.factory.predicateFactory.AbstractSearchRequestPredicateFactory;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractSearchService<Dbo extends AbstractDbo, Dto extends AbstractDto, T extends PageableSearchRequest>
    extends AbstractService<Dbo, Dto> {

    private final AbstractSearchRequestPredicateFactory<T> predicateFactory;

    AbstractSearchService(AbstractSearchRequestPredicateFactory<T> predicateFactory,
                          final AbstractDboDtoConverter<Dbo, Dto> converter,
                          final CustomRepository<Dbo, Long> repository) {
        super(converter, repository);
        this.predicateFactory = predicateFactory;
    }


    @Transactional
    public PageDto<Dto> search(final T searchRequest) {
        final Page<Dbo> page = searchDbo(searchRequest);

        final PageDto<Dto> pageDto = new PageDto<>();
        pageDto.setTotalCount(page.getTotalElements());
        pageDto.setElements(converter.convertToDto(page.getContent(), Collectors.toList()));

        return pageDto;
    }

    @Transactional
    public Page<Dbo> searchDbo(final T searchRequest) {
        final OffsetLimitRequest offsetLimitRequest = new OffsetLimitRequest(searchRequest);
        final Optional<Predicate> optionalPredicate = predicateFactory.build(searchRequest);

        return optionalPredicate
            .map(predicate -> repository.findAll(predicate, offsetLimitRequest))
            .orElseGet(() -> repository.findAll(offsetLimitRequest));
    }
}
