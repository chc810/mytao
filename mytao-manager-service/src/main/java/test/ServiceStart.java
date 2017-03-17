package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <dl>
 * <dt>ServiceStart</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2016/12/1</dd>
 * </dl>
 *
 * @author cuihc
 */
public class ServiceStart {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/dubbo-bootstrap.xml");
        applicationContext.start();
        synchronized (ServiceStart.class) {
            try {
                ServiceStart.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
