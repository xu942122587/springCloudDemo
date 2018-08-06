package com.ruixun.sincfin.mobile.api.util;

import java.util.List;

/**
 * Created by tiantiea on 2018/6/13.
 */
public interface ReportListExcel {

    List<String> getColumnNames();

    List<Object> getRowData(int index);

}
