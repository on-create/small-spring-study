package springframework;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import springframework.bean.UserService;
import springframework.context.support.ClassPathXmlApplicationContext;

public class ApiTest {

    @Test
    public void test_prototype() {
        // 1.��ʼ�� BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. ��ȡBean������÷���
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);

        // 3. ���� scope="prototype/singleton"
        System.out.println("userService01: " + userService01);
        System.out.println("userService02: " + userService02);

        // 4. ��ӡʮ�����ƹ�ϣ
        System.out.println(userService01 + " ʮ�����ƹ�ϣ��" + Integer.toHexString(userService01.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());
    }

    @Test
    public void test_factory_bean() {
        // 1.��ʼ�� BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        // 2. ���ô�����
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("���Խ����" + userService.queryUserInfo());
    }

}
