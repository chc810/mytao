package com.mytao.common.utils;

import java.util.Random;

/**
 * <dl>
 * <dt>Idutil</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/28</dd>
 * </dl>
 *
 * @author cuihc
 */
public class Idutil {

    public static long getId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);
        return id;

    }

    public static void main(String[] args) {
        System.out.println(getId());
    }
}
