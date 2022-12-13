package springframework.test;

import org.junit.Test;
import springframework.BeanDefinition;
import springframework.BeanFactory;
import springframework.test.bean.UserService;

public class ApiTest {

    @Test
    public void testBeanFactory() {
        // 1.初始化 BeanFactory
        BeanFactory factory = new BeanFactory();

        // 2.注入 bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        factory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取 bean
        UserService service = (UserService) factory.getBean("userService");
        service.queryUserInfo();
    }
}
