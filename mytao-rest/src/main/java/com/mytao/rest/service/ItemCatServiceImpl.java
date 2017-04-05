package com.mytao.rest.service;

import com.mytao.manager.facade.pojo.TbItemCatPo;
import com.mytao.manager.facade.service.TbItemCatFacade;
import com.mytao.rest.po.ItemCatListPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <dl>
 * <dt>ItemCatServiceImpl</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/31</dd>
 * </dl>
 *
 * @author cuihc
 */
@Service
public class ItemCatServiceImpl {

    @Autowired
    private TbItemCatFacade tbItemCatService;

    public List<?> listItemCat(Long parentId) {

        List<TbItemCatPo> tbItemCatPoList = tbItemCatService.getItemCatListByParentId(String.valueOf(parentId));
        List ret = new ArrayList();
        for (TbItemCatPo tbItemCatPo : tbItemCatPoList) {
            if (tbItemCatPo.getIsParent()) {
                //父节点
                ItemCatListPo itemCatListPo = new ItemCatListPo();
                itemCatListPo.setUrl("/products/" + tbItemCatPo.getId() + ".html");
                itemCatListPo.setDesc("<a href='/products/" + tbItemCatPo.getId()+ ".html'>" + tbItemCatPo.getName() + "</a>");
                itemCatListPo.setSubs(listItemCat(tbItemCatPo.getId()));
                ret.add(itemCatListPo);
            } else {
                //叶子节点
                ret.add("/products/" + tbItemCatPo.getId() + ".html|" + tbItemCatPo.getName());
            }
        }
        return ret;

    }
}
