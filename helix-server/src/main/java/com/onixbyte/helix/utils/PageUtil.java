package com.onixbyte.helix.utils;

import com.onixbyte.helix.domain.common.Page;
import com.onixbyte.helix.domain.web.request.PageRequest;

import java.util.Optional;

/**
 * Utility class for pagination parameter handling, providing static methods for
 * pagination operations.
 * <p>
 * This utility class is used to process pagination requests, providing default value setting and
 * offset calculation functionality. When pagination parameters are null or invalid, default values
 * are automatically used.
 *
 * @author zihluwang
 * @since 1.0.0
 */
public class PageUtil {

    /**
     * Gets the page number, returning default value 1 if the request is null or page number
     * is null.
     *
     * @param request the pagination request object, may be null
     * @return the page number, defaults to 1
     */
    public static long getPageNum(PageRequest request) {
        return Optional.ofNullable(request)
                .map(PageRequest::getPageNum)
                .orElse(1L);
    }

    /**
     * Gets the page size, returning default value 10 if the request is null or page size is null.
     *
     * @param request the pagination request object, may be null
     * @return the page size, defaults to 10
     */
    public static long getPageSize(PageRequest request) {
        return Optional.ofNullable(request)
                .map(PageRequest::getPageSize)
                .orElse(10L);
    }

    /**
     * Calculates the offset for database queries.
     * <p>
     * Offset calculation formula: (pageNumber - 1) × pageSize. This method automatically handles
     * null value situations, using default page number and page size.
     *
     * @param request the pagination request object, may be null
     * @return the calculated offset for database pagination queries
     */
    public static long getOffset(PageRequest request) {
        var pageNum = getPageNum(request);
        var pageSize = getPageSize(request);

        return (pageNum - 1) * pageSize;
    }

    /**
     * Calculates the offset for database queries using specific page number and page size.
     * <p>
     * Offset calculation formula: (pageNumber - 1) × pageSize. This overloaded method accepts
     * explicit page number and page size parameters instead of a PageRequest object.
     *
     * @param pageNum  the page number, must be positive
     * @param pageSize the page size, must be positive
     * @return the calculated offset for database pagination queries
     */
    public static long getOffset(long pageNum, long pageSize) {
        return (pageNum - 1) * pageSize;
    }

    /**
     * Creates a Page object from the pagination request.
     * <p>
     * This method extracts page number and page size from the request and creates a new Page
     * instance. If the request is null or contains null values, default values will be used
     * (page number defaults to 1, page size defaults to 10).
     *
     * @param request the pagination request object, may be null
     * @param <T>     the type parameter for the Page object
     * @return a new Page instance with the specified pagination parameters
     */
    public static <T> Page<T> getPage(PageRequest request) {
        var pageNum = getPageNum(request);
        var pageSize = getPageSize(request);
        return new Page<>(pageNum, pageSize);
    }
}
