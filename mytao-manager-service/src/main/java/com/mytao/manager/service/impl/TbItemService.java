package com.mytao.manager.service.impl;

import com.mytao.common.service.BaseService;
import com.mytao.manager.entity.TbItem;
import com.mytao.manager.facade.pojo.TbItemPo;
import com.mytao.manager.facade.service.TbItemFacade;
import com.mytao.manager.mapper.TbItemMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <dl>
 * <dt>TbItemService</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/15</dd>
 * </dl>
 *
 * @author cuihc
 */
@Service
public class TbItemService extends BaseService implements TbItemFacade  {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public TbItemPo getById(Long id) {
        logger.info("进入TbItemService.getById()...id=" + id);
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(id);
        TbItemPo tbItemPo = new TbItemPo();
        BeanUtils.copyProperties(tbItem, tbItemPo);
        return tbItemPo;
    }
}
