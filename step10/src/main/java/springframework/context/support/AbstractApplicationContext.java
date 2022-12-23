package springframework.context.support;

import springframework.beans.BeansException;
import springframework.beans.factory.ConfigurableListableBeanFactory;
import springframework.beans.factory.config.BeanFactoryPostProcessor;
import springframework.beans.factory.config.BeanPostProcessor;
import springframework.context.ApplicationEvent;
import springframework.context.ApplicationListener;
import springframework.context.ConfigurableApplicationContext;
import springframework.context.event.ApplicationEventMulticaster;
import springframework.context.event.ContextClosedEvent;
import springframework.context.event.ContextRefreshedEvent;
import springframework.context.event.SimpleApplicationEventMulticaster;
import springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * Abstract implementation of the {@link springframework.context.ApplicationContext}
 * interface. Doesn't mandate the type of storage used for configuration; simply
 * implements common context functionality. Uses the Template Method design pattern,
 * requiring concrete subclasses to implement abstract methods.
 * <p>����Ӧ��������<p>
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(beanName, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public void refresh() throws BeansException {
        // 1.���� BeanFactory�������� BeanDefinition
        refreshBeanFactory();

        // 2.��ȡ BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3.��� ApplicationContextAwareProcessor���ü̳��� ApplicationContextAware �� Bean �����ܸ�֪������ ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 4.�� Bean ʵ����֮ǰ��ִ�� BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // 5.BeanPostProcessor ��Ҫ��ǰ������ Bean ����ʵ����֮ǰִ��ע�����
        registerBeanPostProcessors(beanFactory);

        // 6. ��ʼ���¼�������
        initApplicationEventMulticaster();

        // 7. ע���¼�������
        registerListeners();

        // 8.��ǰʵ�������� bean ����
        beanFactory.preInstantiateSingletons();

        // 9. ��������ˢ������¼�
        finishRefresh();
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        // ע��Ӧ�ó����¼��ಥ��
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> map = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : map.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> map = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : map.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        // ���������ر��¼�
        publishEvent(new ContextClosedEvent(this));

        // ִ�����ٵ��� bean �����ٷ���
        getBeanFactory().destroySingletons();
    }
}
