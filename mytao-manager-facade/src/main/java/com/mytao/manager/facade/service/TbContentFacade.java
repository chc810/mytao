package com.mytao.manager.facade.service;

import com.mytao.manager.facade.pojo.TbContentCategoryPo;
import com.mytao.manager.facade.pojo.TbContentPo;

import java.util.List;

/**
 * <dl>
 * <dt>TbContentFacade</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/31</dd>
 * </dl>
 *
 * @author cuihc
 */
public interface TbContentFacade {

    List<TbContentCategoryPo> getContentCatList(Long parentId);

    TbContentPo addContent(TbContentPo tbContentPo);

    TbContentCategoryPo addContentCategory(TbContentCategoryPo tbContentCategoryPo);

    TbContentCategoryPo deleteContentCategory(TbContentCategoryPo tbContentCategoryPo);

    TbContentCategoryPo updateContentCategory(TbContentCategoryPo tbContentCategoryPo);
}
