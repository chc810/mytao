package com.mytao.rest.controller;

import com.mytao.rest.service.ItemCatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <dl>
 * <dt>ItemController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/30</dd>
 * </dl>
 *
 * @author cuihc
 */
@Controller
public class ItemController {

    @Autowired
    private ItemCatServiceImpl itemCatService;

    @RequestMapping("/rest/itemcat/list")
    @ResponseBody
    public MappingJacksonValue itemCatList(String callback) {

        List list = itemCatService.listItemCat(0L);
        list = list.subList(0,14);
        Map<String, List> ret = new HashMap<>();
        ret.put("data",list);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(ret);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }

}
