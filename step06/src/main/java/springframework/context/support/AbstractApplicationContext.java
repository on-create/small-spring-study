package springframework.context.support;

import springframework.beans.BeansException;
import springframework.beans.factory.ConfigurableListableBeanFactory;
import springframework.beans.factory.config.BeanFactoryPostProcessor;
import springframework.beans.factory.config.BeanPostProcessor;
import springframework.context.ConfigurableApplicationContext;
import springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * Abstract implementation of the {@link springframework.context.ApplicationContext}
 * interface. Doesn't mandate the type of storage used for configuration; simply
 * implements common context functionality. Uses the Template Method design pattern,
 * requiring concrete subclasses to implement abstract methods.
 * <p>
 * ����Ӧ��������
 * <p>
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

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

        // 3.�� Bean ʵ����֮ǰ��ִ�� BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // 4.BeanPostProcessor ��Ҫ��ǰ������ Bean ����ʵ����֮ǰִ��ע�����
        registerBeanPostProcessors(beanFactory);

        // 5.��ǰʵ�������� bean ����
        beanFactory.preInstantiateSingletons();
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
}
