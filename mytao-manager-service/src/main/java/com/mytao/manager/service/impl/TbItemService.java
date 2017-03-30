package com.mytao.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mytao.common.service.BaseService;
import com.mytao.common.utils.Idutil;
import com.mytao.manager.entity.*;
import com.mytao.manager.facade.pojo.TbItemDescPo;
import com.mytao.manager.facade.pojo.TbItemParamPo;
import com.mytao.manager.facade.pojo.TbItemPo;
import com.mytao.manager.facade.service.TbItemFacade;
import com.mytao.manager.mapper.TbItemDescMapper;
import com.mytao.manager.mapper.TbItemMapper;
import com.mytao.manager.mapper.TbItemParamMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public TbItemPo getById(Long id) {
        logger.info("进入TbItemService.getById()...id=" + id);
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(id);
        TbItemPo tbItemPo = new TbItemPo();
        BeanUtils.copyProperties(tbItem, tbItemPo);
        return tbItemPo;
    }

    @Override
    public TbItemParamPo queryTbItemParamById(Long id) {
        logger.info("进入TbItemService.queryTbItemParamById()...id=" + id);
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        tbItemParamExample.createCriteria().andItemCatIdEqualTo(id);
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);
        if (tbItemParams.size() == 0) {
            return null;
        }
        TbItemParamPo tbItemParamPo = new TbItemParamPo();
        BeanUtils.copyProperties(tbItemParams.get(0),tbItemParamPo);
        return tbItemParamPo;
    }

    @Override
    public List<TbItemPo> listItem(TbItemPo po, int page, int rows) {
        PageHelper.startPage(page,rows);
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> list = tbItemMapper.selectByExample(tbItemExample);
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        pageInfo.getTotal();
        List<TbItemPo> ret = new ArrayList<>();
        for (TbItem tbItem : list) {
            TbItemPo tbItemPo = new TbItemPo();
            BeanUtils.copyProperties(tbItem, tbItemPo);
            ret.add(tbItemPo);
        }
        return ret;
    }

    @Override
    public Map<Long, List<TbItemPo>> listItemWithTotal(TbItemPo po, int page, int rows) {
        PageHelper.startPage(page,rows);
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> list = tbItemMapper.selectByExample(tbItemExample);
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        List<TbItemPo> ret = new ArrayList<>();
        for (TbItem tbItem : list) {
            TbItemPo tbItemPo = new TbItemPo();
            BeanUtils.copyProperties(tbItem, tbItemPo);
            ret.add(tbItemPo);
        }
        Map<Long, List<TbItemPo>> map = new HashMap<>();
        map.put(pageInfo.getTotal(), ret);
        return map;
    }

    @Override
    public TbItemPo saveItem(TbItemPo po, String desc) {
        long id = Idutil.getId();
        TbItem tbItem = new TbItem();
        BeanUtils.copyProperties(po, tbItem);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        tbItem.setId(id);
        tbItem.setStatus((byte)1);
        int tbItemRet = tbItemMapper.insert(tbItem);
        logger.info("tbItemRet=" + tbItemRet);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(id);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());
        tbItemDesc.setItemDesc(desc);
        int tbItemDescRet = tbItemDescMapper.insert(tbItemDesc);
        logger.info("tbItemDescRet=" + tbItemDescRet);
        return po;
    }

    @Override
    public TbItemParamPo saveItemParam(TbItemParamPo po) {
        TbItemParam tbItemParam = new TbItemParam();
        BeanUtils.copyProperties(po, tbItemParam);
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());
        tbItemParamMapper.insertSelective(tbItemParam);
        return po;
    }

    @Override
    public TbItemPo updateItem(TbItemPo po, String desc) {
        TbItem tbItem = new TbItem();
        BeanUtils.copyProperties(po, tbItem);
        tbItem.setUpdated(new Date());

        int tbItemRet = tbItemMapper.updateByPrimaryKeySelective(tbItem);
        logger.info("tbItemRet=" + tbItemRet);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(po.getId());
        tbItemDesc.setUpdated(new Date());
        tbItemDesc.setItemDesc(desc);
        int tbItemDescRet = tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
        logger.info("tbItemDescRet=" + tbItemDescRet);
        return po;
    }

    public TbItemDescPo getTbItemDescById(Long id) {
        logger.info("进入TbItemService.getTbItemDescById()...id=" + id);
        //如果字段有blob类型的参数，逆向工程生产的代码中，需要使用selectByExampleWithBLOBs查询才能查到blob类型的内容
        TbItemDescExample tbItemDescExample = new TbItemDescExample();
        tbItemDescExample.createCriteria().andItemIdEqualTo(id);
        List<TbItemDesc> tbItemDescs = tbItemDescMapper.selectByExampleWithBLOBs(tbItemDescExample);
        if (tbItemDescs.size()>0) {
            TbItemDescPo tbItemDescPo = new TbItemDescPo();
            BeanUtils.copyProperties(tbItemDescs.get(0), tbItemDescPo);
            return tbItemDescPo;
        }
        return null;
    }

    @Override
    public Map<Long, List<TbItemParamPo>> listItemParam(TbItemParamPo po, int page, int rows) {
        PageHelper.startPage(page,rows);
        List<TbItemParam> list = tbItemParamMapper.selectWithCatName();
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
        List<TbItemParamPo> ret = new ArrayList<>();
        for (TbItemParam tbItemParam : list) {
            TbItemParamPo tbItemParamPo = new TbItemParamPo();
            BeanUtils.copyProperties(tbItemParam, tbItemParamPo);
            ret.add(tbItemParamPo);
        }
        Map<Long, List<TbItemParamPo>> map = new HashMap<>();
        map.put(pageInfo.getTotal(), ret);
        return map;
    }
}
