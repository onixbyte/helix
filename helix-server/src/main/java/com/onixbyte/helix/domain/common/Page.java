package com.onixbyte.helix.domain.common;

import java.util.List;
import java.util.Objects;

public class Page<T> {

    private List<T> records;

    private Long total;

    private Long pageSize;

    private Long pageNum;

    public Page() {
    }

    public Page(List<T> records, Long total, Long pageSize, Long pageNum) {
        this.records = records;
        this.total = total;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        var page = (Page<?>) o;
        return Objects.equals(records, page.records) &&
                Objects.equals(total, page.total) &&
                Objects.equals(pageSize, page.pageSize) &&
                Objects.equals(pageNum, page.pageNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(records, total, pageSize, pageNum);
    }

    @Override
    public String toString() {
        return "Page{" +
                "records=" + records +
                ", total=" + total +
                ", pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                '}';
    }
}
