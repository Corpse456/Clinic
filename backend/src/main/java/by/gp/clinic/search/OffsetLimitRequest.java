package by.gp.clinic.search;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;

@Data
public class OffsetLimitRequest implements Pageable {

    private final long offset;
    private final int pageSize;
    private Sort sort;

    private OffsetLimitRequest(final long offset, final int pageSize) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset can't be less than 0");
        }

        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size limit can't be less than 1");
        }

        this.offset = offset;
        this.pageSize = pageSize;
        this.sort = Sort.unsorted();
    }

    public OffsetLimitRequest(final PageableSearchRequest pageableSearchRequest) {
        this(pageableSearchRequest.getOffset(), pageableSearchRequest.getLimit());
    }

    @Override
    public int getPageNumber() {
        return (int) (offset / pageSize) + 1;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    @NonNull
    public Sort getSort() {
        return sort;
    }

    @Override
    @NonNull
    public Pageable next() {
        return this;
    }

    @Override
    public boolean isPaged() {
        return true;
    }

    @Override
    @NonNull
    public Pageable previousOrFirst() {
        return this;
    }

    @Override
    @NonNull
    public Pageable first() {
        return this;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    public static void main(String[] args) {
        final OffsetLimitRequest offsetLimitRequest = new OffsetLimitRequest(new PageableSearchRequest());
        System.out.println("offsetLimitRequest = " + offsetLimitRequest.getPageNumber());
    }
}
