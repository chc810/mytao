package com.mytao.manager.service.impl;

import com.mytao.common.service.BaseService;
import com.mytao.manager.entity.TbItemCat;
import com.mytao.manager.entity.TbItemCatExample;
import com.mytao.manager.facade.pojo.TbItemCatPo;
import com.mytao.manager.facade.service.TbItemCatFacade;
import com.mytao.manager.mapper.TbItemCatMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <dl>
 * <dt>TbItemCatService</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/17</dd>
 * </dl>
 *
 * @author cuihc
 */
@Service
public class TbItemCatService extends BaseService implements TbItemCatFacade{

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TbItemCatPo> getItemCatListByParentId(String id) {
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        tbItemCatExample.createCriteria().andParentIdEqualTo(Long.parseLong(id));
        List<TbItemCat> tbItemCatList = tbItemCatMapper.selectByExample(tbItemCatExample);
        List<TbItemCatPo> retList = new ArrayList<>();
        for (TbItemCat cat : tbItemCatList) {
            TbItemCatPo itemCatPo = new TbItemCatPo();
            BeanUtils.copyProperties(cat, itemCatPo);
            retList.add(itemCatPo);
        }
        return retList;
    }
}
