package springframework.test;

import org.junit.Test;
import springframework.beans.factory.config.BeanDefinition;
import springframework.beans.factory.support.DefaultListableBeanFactory;
import springframework.test.bean.UserService;

public class ApiTest {

    @Test
    public void testBeanFactory() {
        // 1.��ʼ�� BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.ע�� bean
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class));

        // 3.��һ�λ�ȡ bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4.�ڶ��λ�ȡ bean from Singleton
        UserService userService_Singleton = (UserService) beanFactory.getSingleton("userService");
        userService_Singleton.queryUserInfo();

        System.out.println(userService == userService_Singleton);
    }
}
