package springframework.context.event;

import springframework.beans.BeansException;
import springframework.beans.factory.BeanFactory;
import springframework.beans.factory.BeanFactoryAware;
import springframework.context.ApplicationEvent;
import springframework.context.ApplicationListener;
import springframework.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Abstract implementation of the {@link ApplicationEventMulticaster} interface,
 * providing the basic listener registration facility.
 * <p>
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    private final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public final void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * ���ڻ���ļ�������Ϣ������ƥ������� event �ļ��������ϡ�
     * @param event Ҫ�������¼�.
     * @return a Collection of ApplicationListeners
     * @see springframework.context.ApplicationListener
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener, event)) {
                allListeners.add(listener);
            }
        }
        return allListeners;
    }

    /**
     * �������Ƿ�Ը��¼�����Ȥ
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> listener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = listener.getClass();

        // ���� CglibSubclassingInstantiationStrategy��SimpleInstantiationStrategy ��ͬ��ʵ�������ͣ���Ҫ�жϺ��ȡĿ�� class
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        // ����ʵ�ֽӿ���Ϣ�� Type ���飬����������Ϣ
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        // ��ȡ���Ͳ�������
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClass;
        try {
            eventClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }

        /**
         * �ж��� eventClass ��������ʾ�����ӿ���ָ���� event.getClass() ��������ʾ�����ӿ��Ƿ���ͬ�����Ƿ����䳬��򳬽ӿڡ�
         * isAssignableFrom�������ж�����͸���Ĺ�ϵ�ģ����߽ӿڵ�ʵ����ͽӿڵĹ�ϵ�ģ�Ĭ�����е�����ռ����඼��Object��
         * ���A.isAssignableFrom(B)�����true��֤��B����ת����ΪA,Ҳ����A������Bת��������
         */
        return eventClass.isAssignableFrom(event.getClass());
    }
}
