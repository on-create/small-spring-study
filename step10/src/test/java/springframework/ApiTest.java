package springframework;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;
import springframework.context.support.ClassPathXmlApplicationContext;
import springframework.event.CustomEvent;
import springframework.event.CustomEventListener;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ApiTest {

    @Test
    public void test_proxy() throws Exception {
        CustomEvent customEvent = new CustomEvent("customEvent", 111111111111L, "success");
        CustomEventListener simpleCreate = simpleCreate();
        CustomEventListener cglibCreate = cglibCreate();
        System.out.println(simpleCreate.getClass().getSuperclass());
        // class springframework.event.CustomEventListener
        System.out.println(cglibCreate.getClass().getSuperclass());

        // springframework.context.ApplicationListener<springframework.event.CustomEvent>
        Type type = CustomEventListener.class.getGenericInterfaces()[0];
        Type t = ((ParameterizedType) type).getActualTypeArguments()[0];
    }

    private CustomEventListener simpleCreate() throws Exception {
        // springframework.event.CustomEventListener@20fa23c1
        // class springframework.event.CustomEventListener
        return CustomEventListener.class.getDeclaredConstructor().newInstance();
    }

    private CustomEventListener cglibCreate() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CustomEventListener.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        // springframework.event.CustomEventListener$$EnhancerByCGLIB$$fb4ec00c@3ecf72fd
        // class springframework.event.CustomEventListener$$EnhancerByCGLIB$$fb4ec00c
        return (CustomEventListener) enhancer.create();
    }

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "≥…π¶¡À£°"));

        applicationContext.registerShutdownHook();
    }

}
