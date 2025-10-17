package com.onixbyte.helix.domain.common;

import com.onixbyte.helix.exception.BizException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 * Page Request.
 *
 * @author zihluwang
 */
public class PageRequest implements Pageable {

    private int pageNumber;
    private int pageSize;
    private Sort sort;
    private boolean paged;

    public PageRequest(int pageNumber, int pageSize, Sort sort, boolean paged) {
        this.paged = paged;
        if (paged) {
            if (pageNumber <= 0) {
                throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "Page number should not less than 0.");
            }

            if (pageSize <= 0) {
                throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "Page size should not less than 0.");
            }
        }
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = Optional.ofNullable(sort)
                .orElse(Sort.unsorted());
    }

    public PageRequest() {
        this.pageNumber = 1;
        this.pageSize = 10;
        this.sort = Sort.unsorted();
        this.paged = true;
    }

    public static PageRequest of(int pageNumber, int pageSize) {
        return new PageRequest(pageNumber, pageSize, Sort.unsorted(), true);
    }

    public static PageRequest of(int pageNumber, int pageSize, Sort sort) {
        return new PageRequest(pageNumber, pageSize, sort, true);
    }

    public static PageRequest of(int pageNumber, int pageSize, Sort.Direction direction, String... properties) {
        return new PageRequest(pageNumber, pageSize, Sort.by(direction, properties), true);
    }

    public static PageRequest unpaged() {
        return new PageRequest(0, 0, Sort.unsorted(), false);
    }

    @Override
    public boolean isPaged() {
        return paged;
    }

    public void setPaged(boolean paged) {
        this.paged = paged;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getOffset() {
        return (long) (pageNumber - 1) * pageSize;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    /**
     * Returns the sorting parameters.
     *
     * @return sorting parameters
     */
    @Override
    public Sort getSort() {
        return sort;
    }

    /**
     * Returns the next {@code Paginator}.
     *
     * @return next {@code Paginator}
     * @throws UnsupportedOperationException if this is an unpaged request
     */
    @Override
    public PageRequest next() {
        if (!paged) {
            throw new UnsupportedOperationException("Cannot get next page for an unpaged request.");
        }
        return PageRequest.of(pageNumber + 1, pageSize, sort);
    }

    /**
     * Returns the previous {@code Paginator}.
     *
     * @return previous {@code Paginator}
     * @throws UnsupportedOperationException if this is an unpaged request
     */
    @Override
    public PageRequest previousOrFirst() {
        if (!paged) {
            throw new UnsupportedOperationException("Cannot get next page for an unpaged request.");
        }

        var _pageNumber = pageNumber - 1;
        if (_pageNumber <= 0) {
            _pageNumber = 1;
        }
        return PageRequest.of(_pageNumber, pageSize, sort);
    }

    /**
     * Returns the first page.
     *
     * @return first page
     * @throws UnsupportedOperationException if this is an unpaged request
     */
    @Override
    public PageRequest first() {
        if (!paged) {
            throw new UnsupportedOperationException("Cannot get next page for an unpaged request.");
        }

        return PageRequest.of(1, pageSize, sort);
    }

    /**
     * Creates a new {@link PageRequest} with {@code pageNumber} applied.
     *
     * @param pageNumber specific page number
     * @return a new {@link PageRequest} or throws {@link IllegalArgumentException} if the
     * {@code pageSize} less than zero
     * @throws IllegalArgumentException      if {@code pageNumber} less than zero
     * @throws UnsupportedOperationException if this is an unpaged request
     */
    @Override
    public PageRequest withPage(int pageNumber) {
        if (!paged) {
            throw new UnsupportedOperationException("Cannot get next page for an unpaged request.");
        }

        if (pageNumber <= 0) {
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "Paginator number should not less than 0.");
        }
        return PageRequest.of(pageNumber, pageSize, sort);
    }

    /**
     * Returns whether there's a previous {@link PageRequest} we can access from the current one.
     * Will return {@literal false} in case the current {@link Pageable} already refers to the
     * first page.
     *
     * @return whether there's a previous {@link PageRequest}
     */
    @Override
    public boolean hasPrevious() {
        return pageNumber > 1;
    }
}
