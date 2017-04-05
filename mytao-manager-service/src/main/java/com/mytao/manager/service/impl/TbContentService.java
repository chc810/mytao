package com.mytao.manager.service.impl;

import com.mytao.common.service.BaseService;
import com.mytao.manager.entity.TbContent;
import com.mytao.manager.entity.TbContentCategory;
import com.mytao.manager.entity.TbContentCategoryExample;
import com.mytao.manager.facade.pojo.TbContentCategoryPo;
import com.mytao.manager.facade.pojo.TbContentPo;
import com.mytao.manager.facade.service.TbContentFacade;
import com.mytao.manager.mapper.TbContentCategoryMapper;
import com.mytao.manager.mapper.TbContentMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <dl>
 * <dt>TbContentService</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/31</dd>
 * </dl>
 *
 * @author cuihc
 */
@Service
public class TbContentService extends BaseService implements TbContentFacade,ApplicationContextAware {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Autowired
    private TbContentMapper tbContentMapper;


    @Override
    public List<TbContentCategoryPo> getContentCatList(Long parentId) {
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        tbContentCategoryExample.createCriteria().andParentIdEqualTo(parentId);

        List<TbContentCategory> tbContentCategoryList =  tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        List<TbContentCategoryPo> ret = new ArrayList<>();
        for (TbContentCategory tbContentCategory : tbContentCategoryList) {
            TbContentCategoryPo tbContentCategoryPo = new TbContentCategoryPo();
            BeanUtils.copyProperties(tbContentCategory, tbContentCategoryPo);
            ret.add(tbContentCategoryPo);
        }
        return ret;
    }

    @Override
    public TbContentPo addContent(TbContentPo tbContentPo) {
        TbContent tbContent = new TbContent();
        BeanUtils.copyProperties(tbContentPo, tbContent);


        return null;
    }

    @Override
    public TbContentCategoryPo addContentCategory(TbContentCategoryPo tbContentCategoryPo) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        BeanUtils.copyProperties(tbContentCategoryPo, tbContentCategory);
        tbContentCategory.setUpdated(new Date());
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setIsParent(false);
        tbContentCategory.setSortOrder(1);
        //状态。可选值:1(正常),2(删除)
        tbContentCategory.setStatus(1);
        //返回主键id
        int ret = tbContentCategoryMapper.insert(tbContentCategory);

        //获取父节点
        TbContentCategory parentContentCat = tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
        if (!parentContentCat.getIsParent()) {
            //之前是叶子节点
            parentContentCat.setIsParent(true);
            parentContentCat.setUpdated(new Date());
            tbContentCategoryMapper.updateByPrimaryKey(parentContentCat);
        }
        tbContentCategoryPo.setId(tbContentCategory.getId());
        return tbContentCategoryPo;
    }


    public TbContentCategoryPo deleteContentCategory(TbContentCategoryPo tbContentCategoryPo) {
        logger.info("this........................................." + this);
        logger.info("applicationContext.getBean(TbContentFacade.class)----------------" + applicationContext.getBean(TbContentFacade.class).toString());
        logger.info("applicationContext.getBean(tbContentService)----------------" + applicationContext.getBean("tbContentService").toString());
        logger.info("applicationContext.getBean(TbContentService.class)----------------" + applicationContext.getBean(TbContentService.class));
        //删除节点
        tbContentCategoryMapper.deleteByPrimaryKey(tbContentCategoryPo.getId());
        //查找父节点
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        tbContentCategoryExample.createCriteria().andParentIdEqualTo(tbContentCategoryPo.getParentId());
        List<TbContentCategory> tbContentCategoryList = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        if (tbContentCategoryList.size() == 0) {
            //没有子节点了
            TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(tbContentCategoryPo.getParentId());
            tbContentCategory.setIsParent(false);
            tbContentCategory.setUpdated(new Date());
            tbContentCategoryMapper.updateByPrimaryKey(tbContentCategory);
        }
        return tbContentCategoryPo;
      /*  Object o = this;
//        TbContentService tbContentService = (TbContentService) this.applicationContext.getBean(TbContentService.class);
        TbContentFacade dd = this.applicationContext.getBean(TbContentFacade.class);
        dd.updateContentCategory(tbContentCategoryPo);

      return null;*/
    }

    @Override
    public TbContentCategoryPo updateContentCategory(TbContentCategoryPo tbContentCategoryPo) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(tbContentCategoryPo.getId());
        tbContentCategory.setUpdated(new Date());
        tbContentCategory.setName(tbContentCategoryPo.getName());
        tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
         int i = 1 / 0;
        return tbContentCategoryPo;
    }

    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }
}
