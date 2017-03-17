package com.mytao.manager.web.controller;

import com.mytao.manager.facade.pojo.TbItemPo;
import com.mytao.manager.facade.service.TbItemFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
