package com.mytao.manager.facade.service;

import com.mytao.manager.facade.pojo.TbItemCatPo;

import java.util.List;

/**
 * <dl>
 * <dt>TbItemCatFacade</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/17</dd>
 * </dl>
 *
 * @author cuihc
 */
public interface TbItemCatFacade {

    List<TbItemCatPo> getItemCatListByParentId(String id);
}
