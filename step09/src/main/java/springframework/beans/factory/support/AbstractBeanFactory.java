package springframework.beans.factory.support;

import springframework.beans.BeansException;
import springframework.beans.factory.FactoryBean;
import springframework.beans.factory.config.BeanDefinition;
import springframework.beans.factory.config.BeanPostProcessor;
import springframework.beans.factory.config.ConfigurableBeanFactory;
import springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * BeanDefinition ע���ӿ�
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    /**
     * ClassLoader to resolve bean class names with, if necessary
     */
    private final ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /**
     * BeanPostProcessors to apply in createBean
     */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String beanName) throws BeansException {
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return (T) getBean(beanName);
    }

    private <T> T doGetBean(final String beanName, final Object[] args) {
        Object sharedInstance  = getSingleton(beanName);
        if (sharedInstance != null) {
            // ����� FactoryBean������Ҫ���� FactoryBean#getObject
            return (T) getObjectForBeanInstance(sharedInstance, beanName);
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        Object bean = createBean(beanName, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, beanName);
    }

    /**
     * beanInstance δʵ�� FactoryBean������Դ���󣨵�����
     *              ʵ���� FactoryBean������ܴ��ڴ����ҵ�����Ϊ�������󣬷����ڴ��еĶ���
     *                  ����Ҳ�������ȡ factoryBean �е� object������ǵ�������ӵ��ڴ��У�
     * @param beanInstance bean ʵ������
     * @param beanName bean ����
     * @return bean
     */
    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);
        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return object;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }
}
