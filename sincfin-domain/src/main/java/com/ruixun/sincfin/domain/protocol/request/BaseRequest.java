package com.ruixun.sincfin.domain.protocol.request;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by t on 2017/4/6.
 */
public class BaseRequest implements Serializable {

    private static final long    serialVersionUID = 8809142367875458974L;

    private static final Integer defaultPageSize  = new Integer(20);
    private static final Integer defaultFirstPage = new Integer(1);
    private static final Integer defaultTotalItem = new Integer(0);

    public static final String   ASC              = "asc";
    public static final String   DESC             = "desc";

    /**
     * 总共多少条
     */
    private Integer              totalItem;
    /**
     * 每页多少条数据
     */
    private Integer              pageSize;
    /**
     * 当前页码
     */
    private Integer              pageIndex;
    private int                  startRow         = -1;
    private int                  endRow           = -1;

    /**
     * 排序字段
     */
    private String               property;
    public static final String   DEFAULT_PROPERTY = "gmt_create";
    /**
     * 排序方向
     * ASC 升序
     * DESC 降序
     */
    private String               direction;
    public static final String   DEFAULT_DIRECTION = DESC;

    private List<Sort>           sorts            = new ArrayList<Sort>();

    public String getProperty() {
        if (StringUtils.isEmpty(property)) {
            return DEFAULT_PROPERTY;
        }
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getDirection() {
        if (StringUtils.isEmpty(direction)) {
            return DEFAULT_DIRECTION;
        }
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }



    protected Integer getDefaultPageSize() {
        return defaultPageSize;
    }

    /**
     * @return Returns the pageIndex.
     */
    public Integer getPageIndex() {
        if (pageIndex == null) {
            return defaultFirstPage;
        }

        return pageIndex;
    }

    /**
     * @param cPage The pageIndex to set.
     */
    public void setPageIndex(Integer cPage) {
        if ((cPage == null) || (cPage.intValue() <= 0)) {
            this.pageIndex = null;
        } else {
            this.pageIndex = cPage;
        }
    }

    private void setStartEndRow() {
        this.startRow = this.getPageSize().intValue() * (this.getPageIndex().intValue() - 1);
        this.endRow = this.startRow + this.getPageSize().intValue();
    }

    /**
     * @return Returns the pageSize.
     */
    public Integer getPageSize() {
        if (pageSize == null) {
            return getDefaultPageSize();
        }

        return pageSize;
    }

    public boolean hasSetPageSize() {
        return pageSize != null;
    }

    /**
     * @param pSize The pageSize to set.
     */
    public void setPageSize(Integer pSize) {
        if ((pSize == null) || (pSize.intValue() <= 0)) {
            this.pageSize = null;
        } else {
            this.pageSize = pSize;
        }
        setStartEndRow();
    }

    /**
     * @return Returns the totalItem.
     */
    public Integer getTotalItem() {
        if (totalItem == null) {
            return defaultTotalItem;
        }

        return totalItem;
    }

    /**
     * @param tItem The totalItem to set.
     */
    public void setTotalItem(Integer tItem) {
        if (tItem == null) {
            throw new IllegalArgumentException("TotalItem can't be null.");
        }

        this.totalItem = tItem;

        int current = this.getPageIndex().intValue();
        int lastPage = this.getTotalPage();

        if (current > lastPage) {
            this.setPageIndex(new Integer(lastPage));
        }
    }

    public int getTotalPage() {
        int pgSize = this.getPageSize().intValue();
        int total = this.getTotalItem().intValue();
        int result = total / pgSize;

        if ((total == 0) || ((total % pgSize) != 0)) {
            result++;
        }

        return result;
    }

    /**
     * @return Returns the endRow.
     */
    public int getEndRow() {
        if (endRow == -1) {
            setStartEndRow();
        }
        return endRow;
    }

    /**
     * @param endRow The endRow to set.
     */
    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    /**
     * @return Returns the startRow.
     */
    public int getStartRow() {
        if (startRow == -1) {
            setStartEndRow();
        }
        return startRow;
    }

    /**
     * @param startRow The startRow to set.
     */
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public List<Sort> getSorts() {
        return sorts;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
