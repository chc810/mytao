package com.mytao.manager.web.controller;

import com.mytao.manager.facade.pojo.TbItemParamPo;
import com.mytao.manager.facade.service.TbItemFacade;
import com.mytao.manager.web.po.EuDataGrid;
import com.mytao.manager.web.po.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <dl>
 * <dt>TbItemCatParamController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/29</dd>
 * </dl>
 *
 * @author cuihc
 */
@Controller
@RequestMapping("/item/param")
public class TbItemCatParamController {

    @Autowired
    private TbItemFacade tbItemService;

    @RequestMapping("/list")
    @ResponseBody
    public EuDataGrid list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "30") int rows) {
        Map<Long, List<TbItemParamPo>> map = tbItemService.listItemParam(null, page, rows);
        EuDataGrid<TbItemParamPo> euDataGrid = new EuDataGrid<>();
        Long total = map.keySet().iterator().next();
        euDataGrid.setTotal(total);
        euDataGrid.setRows(map.get(total));
        return euDataGrid;
    }

    @RequestMapping("/query/itemcatid/{id}")
    @ResponseBody
    public TaotaoResult query(@PathVariable("id") long id) {
        TbItemParamPo po = tbItemService.queryTbItemParamById(id);
        if (po == null) {
            return TaotaoResult.ok();
        }
        return TaotaoResult.ok(po);
    }


    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult save(@PathVariable long cid, String paramData) {
        TbItemParamPo tbItemParamPo = new TbItemParamPo();
        tbItemParamPo.setItemCatId(cid);
        tbItemParamPo.setParamData(paramData);
        tbItemService.saveItemParam(tbItemParamPo);
        return TaotaoResult.ok();
    }
}
