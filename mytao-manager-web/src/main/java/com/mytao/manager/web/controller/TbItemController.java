package com.mytao.manager.web.controller;

import com.mytao.manager.facade.pojo.TbItemDescPo;
import com.mytao.manager.facade.pojo.TbItemPo;
import com.mytao.manager.facade.service.TbItemFacade;
import com.mytao.manager.web.po.EuDataGrid;
import com.mytao.manager.web.po.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <dl>
 * <dt>TbItemController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/16</dd>
 * </dl>
 *
 * @author cuihc
 */
@RequestMapping("/item")
@Controller
public class TbItemController {

    @Autowired
    private TbItemFacade tbItemService;

    @RequestMapping("/getItem/{id}")
    @ResponseBody
    public TbItemPo getItem(@PathVariable("id") String id) {
        TbItemPo po = tbItemService.getById(Long.parseLong(id));
        return po;
    }

    @RequestMapping("/query/item/desc/{id}")
    @ResponseBody
    public TaotaoResult getItemDesc(@PathVariable long id) {
        TbItemDescPo tbItemDescPo = tbItemService.getTbItemDescById(id);
        return TaotaoResult.ok(tbItemDescPo);
    }

    /*public TaotaoResult*/

    @RequestMapping("/list")
    @ResponseBody
    public EuDataGrid listItemEuDataGrid(int page, int rows) {
        Map<Long, List<TbItemPo>> map = tbItemService.listItemWithTotal(null, page, rows);
        EuDataGrid<TbItemPo> euDataGrid = new EuDataGrid<>();
        Long total = map.keySet().iterator().next();
        euDataGrid.setTotal(total);
        euDataGrid.setRows(map.get(total));
        return euDataGrid;
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult save(TbItemPo tbItemPo, String desc, String itemParams) {
        tbItemService.saveItem(tbItemPo,desc, itemParams);
        return TaotaoResult.ok();
    }

    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult update(TbItemPo tbItemPo, String desc) {
        tbItemService.updateItem(tbItemPo,desc);
        return TaotaoResult.ok();
    }
}
