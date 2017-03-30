package com.mytao.manager.web.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 * <dl>
 * <dt>FdfsUtil</dt>
 * <dd>Description:fastdfs操作类</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/27</dd>
 * </dl>
 *
 * @author cuihc
 */
@Component
public class FdfsUtil {

    static Logger logger = LoggerFactory.getLogger(FdfsUtil.class);

    @Value("${file_download_prefix}")
    private String strUrlPrefix;

    //文件上传
    public String fileUpload(byte[] bytes, String strFileName) {
        //上传到dfs
        String strReturn = "";
        TrackerClient tracker = null;
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        StorageClient1 client = null;

        try {
            URL strconfigFileUrl = FdfsUtil.class.getClassLoader().getResource("fdfs_client.conf");
            ClientGlobal.init(strconfigFileUrl.getFile());
            tracker = new TrackerClient();
            trackerServer = tracker.getConnection();
            client = new StorageClient1(trackerServer,
                    storageServer);
            NameValuePair[] metaList = new NameValuePair[1];
            metaList[0] = new NameValuePair("fileName", strFileName);
            String fillExtName = strFileName.split("\\.")[1];
            String fileId = client.upload_file1(bytes, fillExtName, metaList);
            logger.debug(strFileName + " fileid: " + fileId);
            strReturn = strUrlPrefix + fileId;
        } catch (Exception e) {
            logger.error("上传文件出错",e);
            return null;
        } finally {
            try {
                if (trackerServer != null) {
                    trackerServer.close();
                }
                if (storageServer != null) {
                    storageServer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return strReturn;

    }

    /*public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("D:\\tmp\\1.jpg");
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        String ret = FdfsUtil.fileUpload(bytes,"1.jpg","http://10.130.29.182/");
        System.out.println(ret);
    }*/
}
