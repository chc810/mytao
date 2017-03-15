package com.mytao.manager.facade.service;

import com.mytao.manager.facade.pojo.TbItemPo;

/**
 * <dl>
 * <dt>TbItemFacade</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/15</dd>
 * </dl>
 *
 * @author cuihc
 */
public interface TbItemFacade {

    public TbItemPo getById(Long id);
}
