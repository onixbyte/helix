package com.onixbyte.helix.domain.web.request;

import java.util.Objects;

/**
 * Represents a pagination request containing page number and page size information.
 * <p>
 * This class is used to encapsulate pagination parameters for web requests, providing methods to
 * retrieve page information and calculate database offset values.
 *
 * @author zihluwang
 * @since 1.0.0
 */
public class PageRequest {

    /**
     * The page number for pagination, starting from 1.
     */
    private Long pageNum;

    /**
     * The number of items per page.
     */
    private Long pageSize;

    /**
     * Gets the page number.
     *
     * @return the page number, starting from 1
     */
    public Long getPageNum() {
        return pageNum;
    }

    /**
     * Sets the page number.
     *
     * @param pageNum the page number to set, should be greater than 0
     */
    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * Gets the page size.
     *
     * @return the number of items per page
     */
    public Long getPageSize() {
        return pageSize;
    }

    /**
     * Sets the page size.
     *
     * @param pageSize the number of items per page, should be greater than 0
     */
    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public PageRequest() {
    }

    public PageRequest(Long pageNum, Long pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PageRequest that = (PageRequest) o;
        return Objects.equals(pageNum, that.pageNum) && Objects.equals(pageSize, that.pageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNum, pageSize);
    }

    @Override
    public String toString() {
        return "PageRequest{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
