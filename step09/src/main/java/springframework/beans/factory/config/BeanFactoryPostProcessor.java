package springframework.beans.factory.config;

import springframework.beans.BeansException;
import springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * Allows for custom modification of an application context's bean definitions,
 * adapting the bean property values of the context's underlying bean factory.
 *
 * �����Զ����޸� BeanDefinition ������Ϣ
 */
public interface BeanFactoryPostProcessor {

    /**
     * �����е� BeanDefinition ������ɺ�ʵ���� Bean ����֮ǰ���ṩ�޸� BeanDefinition ���ԵĻ���
     *
     * @param beanFactory ConfigurableListableBeanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
