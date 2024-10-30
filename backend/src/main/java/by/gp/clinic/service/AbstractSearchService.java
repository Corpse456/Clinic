package by.gp.clinic.service;

import by.gp.clinic.dbo.AbstractDbo;
import by.gp.clinic.dto.AbstractDto;
import by.gp.clinic.dto.PageDto;
import by.gp.clinic.factory.predicateFactory.AbstractSearchRequestPredicateFactory;
import by.gp.clinic.mapper.AbstractDboDtoMapper;
import by.gp.clinic.repository.CustomRepository;
import by.gp.clinic.search.OffsetLimitRequest;
import by.gp.clinic.search.PageableSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

public abstract class AbstractSearchService<Dbo extends AbstractDbo, Dto extends AbstractDto, T extends PageableSearchRequest>
    extends AbstractService<Dbo, Dto> {

    private final AbstractSearchRequestPredicateFactory<T> predicateFactory;

    AbstractSearchService(AbstractSearchRequestPredicateFactory<T> predicateFactory,
                          final AbstractDboDtoMapper<Dbo, Dto> mapper,
                          final CustomRepository<Dbo, Long> repository) {
        super(mapper, repository);
        this.predicateFactory = predicateFactory;
    }


    @Transactional
    public PageDto<Dto> search(final T searchRequest) {
        final var page = searchDbo(searchRequest);

        final var pageDto = new PageDto<Dto>();
        pageDto.setTotalCount(page.getTotalElements());
        pageDto.setElements(mapper.mapToDto(page.getContent(), Collectors.toList()));

        return pageDto;
    }

    @Transactional
    public Page<Dbo> searchDbo(final T searchRequest) {
        final var offsetLimitRequest = new OffsetLimitRequest(searchRequest);
        final var optionalPredicate = predicateFactory.build(searchRequest);

        return optionalPredicate
            .map(predicate -> repository.findAll(predicate, offsetLimitRequest))
            .orElseGet(() -> repository.findAll(offsetLimitRequest));
    }
}
