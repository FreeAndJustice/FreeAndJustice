package top.cxh.chat.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取Bean
 * @author cxhqz
 *
 */
@Component
public class Beans implements ApplicationContextAware {
 
    @Autowired
    private static ApplicationContext applicationContext;
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Beans.applicationContext == null) {
            Beans.applicationContext = applicationContext;
        }
    }
 
    
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
 
    /**
     * 根据名称获取
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }
 
    /**
     * 根据类获取
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
 
    /**
     * 指定类根据名称获取
     * @param name
     * @param clazz
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}