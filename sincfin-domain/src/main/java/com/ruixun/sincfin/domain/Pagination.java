package com.ruixun.sincfin.domain;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by t on 16/3/14.
 */
public class Pagination<T> implements Serializable {
    /**
     * 当前页，从1开始
     */
    private int pageIndex = 1;

    /**
     * 每页大小
     */
    private int pageSize = 10;

    private int totalCount;

    /**
     * results
     */
    private List<T> data;




    @SuppressWarnings({ "rawtypes", "unchecked" }) private final static Pagination EMPTY = new Pagination(0, 0, Collections.emptyList());

    @SuppressWarnings("unchecked") public static <T> Pagination<T> emptyPagination() {
        return EMPTY;
    }

    public final static int TotalCountNotSupported = -1;

    public Pagination() {
    }

    public Pagination(int pageSize, int pageIndex, List<T> data) {
        this.setPageSize(pageSize);
        this.setPageIndex(pageIndex);
        this.setData(data);
        this.setTotalCount(TotalCountNotSupported);
    }

    public Pagination(BaseRequest baseRequest, Integer totalCount) {
        this(baseRequest.getPageSize(), baseRequest.getPageIndex(), null, totalCount);
    }


    public Pagination(BaseRequest baseQuery, List<T> data, Integer totalCount) {
        this(baseQuery.getPageSize(), baseQuery.getPageIndex(), data, totalCount);
    }

    public Pagination(int pageSize, int pageIndex, List<T> data, Integer totalCount) {
        this.setPageSize(pageSize);
        this.setPageIndex(pageIndex);
        this.setData(data);
        if (totalCount == null) {
            totalCount = 0;
        }
        this.setTotalCount(totalCount);

    }

    /**
     * 是否有下一页
     * @return
     */
    public boolean getHasNext() {
        return getTotalPage() > pageIndex;
    }

    public boolean getIsEmpty() {
        if (getData() != null && getData().size() > 0)
            return false;
        else
            return true;
    }


    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }


    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 总共多少页
     *
     * @return
     */
    public int getTotalPage() {
        int totalCount = this.getTotalCount();
        if (totalCount != TotalCountNotSupported) {
            int page = totalCount / this.getPageSize();
            page += totalCount - this.getPageSize() * page > 0 ? 1 : 0;
            return page;
        } else {
            return TotalCountNotSupported;
        }

    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
