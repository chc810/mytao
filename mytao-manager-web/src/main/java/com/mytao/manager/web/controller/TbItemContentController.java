package com.mytao.manager.web.controller;

import com.mytao.manager.facade.pojo.TbContentCategoryPo;
import com.mytao.manager.facade.pojo.TbContentPo;
import com.mytao.manager.facade.service.TbContentFacade;
import com.mytao.manager.web.po.EuTreeNode;
import com.mytao.manager.web.po.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * <dl>
 * <dt>TbItemContentController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/31</dd>
 * </dl>
 *
 * @author cuihc
 */
@RequestMapping("/content")
@Controller
public class TbItemContentController {

    @Autowired
    private TbContentFacade tbContentService;

    @RequestMapping("/category/list")
    @ResponseBody
    public List<EuTreeNode> listContent(@RequestParam(value = "id", defaultValue = "0") String parentId) {
        List<TbContentCategoryPo> list =  tbContentService.getContentCatList(Long.parseLong(parentId));
        List<EuTreeNode> ret = new ArrayList<>();
        for (TbContentCategoryPo tbContentCategoryPo : list) {
            EuTreeNode euTreeNode = new EuTreeNode();
            euTreeNode.setId(tbContentCategoryPo.getId().toString());
            euTreeNode.setText(tbContentCategoryPo.getName());
            if (tbContentCategoryPo.getIsParent()) {
                //父节点
                euTreeNode.setState("closed");
            } else {
                //叶子
                euTreeNode.setState("open");
            }
            ret.add(euTreeNode);
        }
        return ret;
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult save(TbContentPo tbContentPo) {



        return TaotaoResult.ok();
    }

    @RequestMapping("/category/create")
    @ResponseBody
    public TaotaoResult createContentCat(TbContentCategoryPo po) {
        TbContentCategoryPo ret = tbContentService.addContentCategory(po);
        return TaotaoResult.ok(ret);
    }

    @RequestMapping("/category/delete")
    @ResponseBody
    public TaotaoResult deleteContentCat(TbContentCategoryPo po) {
        TbContentCategoryPo ret = tbContentService.deleteContentCategory(po);
        return TaotaoResult.ok(ret);
    }
    @RequestMapping("/category/update")
    @ResponseBody
    public TaotaoResult updateContentCat(TbContentCategoryPo po) {
        TbContentCategoryPo ret = tbContentService.updateContentCategory(po);
        return TaotaoResult.ok(ret);
    }

}
