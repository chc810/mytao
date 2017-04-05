package com.mytao.rest.po;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * <dl>
 * <dt>ItemCatListPo</dt>
 * <dd>Description:商品分类列表使用的po</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/31</dd>
 * </dl>
 *
 * @author cuihc
 */
public class ItemCatListPo {

    @JsonProperty("u")
    private String url;
    @JsonProperty("n")
    private String desc;
    @JsonProperty("i")
    private List<?> subs;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<?> getSubs() {
        return subs;
    }

    public void setSubs(List<?> subs) {
        this.subs = subs;
    }
}
