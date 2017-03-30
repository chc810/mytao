package com.mytao.manager.web.controller;

import com.mytao.common.controller.BaseController;
import com.mytao.manager.web.po.FileUploadPo;
import com.mytao.manager.web.utils.FdfsUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <dl>
 * <dt>FileUploadController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/17</dd>
 * </dl>
 *
 * @author cuihc
 */
@Controller
public class FileUploadController extends BaseController{

    @Autowired
    private FdfsUtil fdfsUtil;

    //uploadFile这个字符串必须与前端传入的对应
    @RequestMapping("/pic/upload")
    @ResponseBody
    public FileUploadPo uploadPic(MultipartFile uploadFile) {
        FileUploadPo fileUploadPo = new FileUploadPo();
        try {
            byte[] bytes = uploadFile.getBytes();
            String fileName = uploadFile.getOriginalFilename();
            String ret = fdfsUtil.fileUpload(bytes,fileName);
            if (StringUtils.isBlank(ret)) {
                fileUploadPo.error("上传失败");
            } else {
                fileUploadPo.setUrl(ret);
            }
            return fileUploadPo;
        } catch (IOException e) {
            logger.error("上传失败", e);
            fileUploadPo.error("上传失败:" + e.getMessage());
            return fileUploadPo;
        }
    }
}
