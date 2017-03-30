package com.mytao.manager.web.po;

import java.util.List;

/**
 * <dl>
 * <dt>EuDataGrid</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/17</dd>
 * </dl>
 *
 * @author cuihc
 */
public class EuDataGrid<T> {

    private long total;
    private List<T> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
