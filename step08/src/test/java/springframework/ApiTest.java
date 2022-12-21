package springframework;

import org.junit.Test;
import springframework.bean.UserService;
import springframework.context.support.ClassPathXmlApplicationContext;

public class ApiTest {

    @Test
    public void test_xml() {
        // 1.��ʼ�� BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. ��ȡBean������÷���
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("���Խ����" + result);

        System.out.println("ApplicationContextAware��" + userService.getApplicationContext());
        System.out.println("BeanFactoryAware��" + userService.getBeanFactory());

    }

}
