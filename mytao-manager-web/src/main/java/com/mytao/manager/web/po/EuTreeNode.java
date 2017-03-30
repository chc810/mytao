package com.mytao.manager.web.po;

/**
 * <dl>
 * <dt>EuTreeNode</dt>
 * <dd>Description:easyui的tree节点</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/17</dd>
 * </dl>
 *
 * @author cuihc
 */
public class EuTreeNode {

    private String id;
    private String text;
    private String state;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
