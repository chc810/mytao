package com.mytao.manager.web.po;

/**
 * <dl>
 * <dt>FileUploadPo</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/28</dd>
 * </dl>
 *
 * @author cuihc
 */
public class FileUploadPo {

    private int error = 0;
    private String url;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public FileUploadPo error(String url) {
        this.error = 1;
        this.url = url;
        return this;
    }
}
