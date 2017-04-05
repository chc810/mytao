package com.mytao.manager.facade.service;

import com.mytao.manager.facade.pojo.TbItemDescPo;
import com.mytao.manager.facade.pojo.TbItemParamPo;
import com.mytao.manager.facade.pojo.TbItemPo;

import java.util.List;
import java.util.Map;

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

    public TbItemParamPo queryTbItemParamById(Long id);

    public List<TbItemPo> listItem(TbItemPo po, int page, int rows);

    public Map<Long,List<TbItemPo>> listItemWithTotal(TbItemPo po, int page, int rows);

    public TbItemPo saveItem(TbItemPo po, String desc, String itemParams);

    public TbItemParamPo saveItemParam(TbItemParamPo po);

    public TbItemPo updateItem(TbItemPo po, String desc);

    public TbItemDescPo getTbItemDescById(Long id);

    public Map<Long,List<TbItemParamPo>> listItemParam(TbItemParamPo po, int page, int rows);

}
