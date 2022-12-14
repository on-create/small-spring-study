package springframework.test;

import org.junit.Test;
import springframework.beans.factory.config.BeanDefinition;
import springframework.beans.factory.support.DefaultListableBeanFactory;
import springframework.test.bean.UserService;

public class ApiTest {

    @Test
    public void testBeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册 bean
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class));

        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4.第二次获取 bean from Singleton
        UserService userService_Singleton = (UserService) beanFactory.getSingleton("userService");
        userService_Singleton.queryUserInfo();

        System.out.println(userService == userService_Singleton);
    }
}
