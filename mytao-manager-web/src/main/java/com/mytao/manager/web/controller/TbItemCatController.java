package com.mytao.manager.web.controller;

import com.mytao.manager.facade.pojo.TbItemCatPo;
import com.mytao.manager.facade.service.TbItemCatFacade;
import com.mytao.manager.web.po.EuTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * <dl>
 * <dt>TbItemCatController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/16</dd>
 * </dl>
 *
 * @author cuihc
 */
@RequestMapping("/item/cat")
@Controller
public class TbItemCatController {

    @Autowired
    private TbItemCatFacade tbItemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List list(@RequestParam(value = "id",defaultValue = "0") String id) {
        List<TbItemCatPo> list = tbItemCatService.getItemCatListByParentId(id);
        List<EuTreeNode> ret = new ArrayList<>();
        for (TbItemCatPo po: list) {
            EuTreeNode euTreeNode = new EuTreeNode();
            euTreeNode.setId(po.getId().toString());
            euTreeNode.setText(po.getName());
            euTreeNode.setState(po.getIsParent() ? "closed" : "open");
            ret.add(euTreeNode);
        }
        return ret;
    }

}
