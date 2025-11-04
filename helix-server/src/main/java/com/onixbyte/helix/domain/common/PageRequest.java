package com.onixbyte.helix.domain.common;

import com.onixbyte.helix.exception.BizException;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 * A custom implementation of the Spring Framework's {@link Pageable} interface, designed to support
 * both paged and unpaged requests.
 * <p>
 * This class encapsulates pagination information, including page number, page size, and
 * sorting criteria. It provides factory methods for creating paged and unpaged instances, as well
 * as methods for navigation.
 *
 * @author zihluwang
 */
public class PageRequest implements Pageable {

    /**
     * The page number for the current request.
     */
    private int pageNumber;

    /**
     * The number of records per page.
     */
    private int pageSize;

    /**
     * The sorting criteria for the query.
     */
    private Sort sort;

    /**
     * A flag indicating whether the request is paged.
     */
    private boolean paged;

    /**
     * Constructs a new {@code PageRequest} with the specified pagination and sorting parameters.
     *
     * @param pageNumber the page number, which must be greater than 0 for paged requests
     * @param pageSize   the number of records per page, which must be greater than 0 for
     *                   paged requests
     * @param sort       the sorting criteria. If null, {@link Sort#unsorted()} is used
     * @param paged      a boolean flag indicating whether the request is paged
     * @throws BizException if the page number or page size is invalid for a paged request.
     */
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

    /**
     * Constructs a new {@code PageRequest} with default values. Page number is set to 1, page size
     * to 10, and the request is paged.
     */
    public PageRequest() {
        this.pageNumber = 1;
        this.pageSize = 10;
        this.sort = Sort.unsorted();
        this.paged = true;
    }

    /**
     * Creates a new paged {@code PageRequest} with the specified page number and page size.
     *
     * @param pageNumber the page number
     * @param pageSize   the number of records per page
     * @return a new {@code PageRequest} instance
     */
    public static PageRequest of(int pageNumber, int pageSize) {
        return new PageRequest(pageNumber, pageSize, Sort.unsorted(), true);
    }

    /**
     * Creates a new paged {@code PageRequest} with the specified page number, page size, and sort criteria.
     *
     * @param pageNumber the page number
     * @param pageSize   the number of records per page
     * @param sort       the sorting criteria
     * @return a new {@code PageRequest} instance
     */
    public static PageRequest of(int pageNumber, int pageSize, Sort sort) {
        return new PageRequest(pageNumber, pageSize, sort, true);
    }

    /**
     * Creates a new paged {@code PageRequest} with the specified page number, page size, and sort direction.
     *
     * @param pageNumber the page number
     * @param pageSize   the number of records per page
     * @param direction  the sort direction
     * @param properties the properties to sort by
     * @return a new {@code PageRequest} instance
     */
    public static PageRequest of(int pageNumber, int pageSize, Sort.Direction direction, String... properties) {
        return new PageRequest(pageNumber, pageSize, Sort.by(direction, properties), true);
    }

    /**
     * Creates a new unpaged {@code PageRequest}.
     *
     * @return a new unpaged {@code PageRequest} instance
     */
    public static PageRequest unpaged() {
        return new PageRequest(0, 0, Sort.unsorted(), false);
    }

    /**
     * Returns whether the current request is paged.
     *
     * @return {@code true} if the request is paged, {@code false} otherwise
     */
    @Override
    public boolean isPaged() {
        return paged;
    }

    /**
     * Sets whether the request is paged.
     *
     * @param paged {@code true} to make the request paged, {@code false} otherwise
     */
    public void setPaged(boolean paged) {
        this.paged = paged;
    }

    /**
     * Returns the current page number.
     *
     * @return the current page number
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Sets the page number.
     *
     * @param pageNumber the page number to set
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Returns the number of records per page.
     *
     * @return the number of records per page
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the number of records per page.
     *
     * @param pageSize the number of records per page to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Returns the offset to be taken according to the underlying page and page size.
     *
     * @return the offset to be taken
     */
    public long getOffset() {
        return (long) (pageNumber - 1) * pageSize;
    }

    /**
     * Sets the sorting criteria.
     *
     * @param sort the sorting criteria to set
     */
    public void setSort(Sort sort) {
        this.sort = sort;
    }

    /**
     * Returns the sorting parameters.
     *
     * @return the sorting parameters
     */
    @NonNull
    @Override
    public Sort getSort() {
        return sort;
    }

    /**
     * Returns the {@link Pageable} requesting the next page.
     *
     * @return the next {@link Pageable}
     * @throws UnsupportedOperationException if this is an unpaged request
     */
    @NonNull
    @Override
    public PageRequest next() {
        if (!paged) {
            throw new UnsupportedOperationException("Cannot get next page for an unpaged request.");
        }
        return PageRequest.of(pageNumber + 1, pageSize, sort);
    }

    /**
     * Returns the {@link PageRequest} requesting the previous page, or the first page if the
     * current page is the first one.
     *
     * @return the previous or first {@link PageRequest}
     * @throws UnsupportedOperationException if this is an unpaged request
     */
    @NonNull
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
     * Returns the {@link PageRequest} requesting the first page.
     *
     * @return the first {@link PageRequest}
     * @throws UnsupportedOperationException if this is an unpaged request
     */
    @NonNull
    @Override
    public PageRequest first() {
        if (!paged) {
            throw new UnsupportedOperationException("Cannot get next page for an unpaged request.");
        }

        return PageRequest.of(1, pageSize, sort);
    }

    /**
     * Creates a new {@link PageRequest} with the given page number.
     *
     * @param pageNumber the new page number
     * @return a new {@link PageRequest}
     * @throws UnsupportedOperationException if this is an unpaged request
     */
    @NonNull
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
     * Returns whether there is a previous page.
     *
     * @return {@code true} if there is a previous page, {@code false} otherwise
     */
    @Override
    public boolean hasPrevious() {
        return pageNumber > 1;
    }
}
