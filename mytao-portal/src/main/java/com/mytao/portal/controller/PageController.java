package com.mytao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <dl>
 * <dt>PageController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/30</dd>
 * </dl>
 *
 * @author cuihc
 */
@Controller
public class PageController {

    @RequestMapping("/{page}")
    public String index(@PathVariable String page) {
        return page;
    }
}
