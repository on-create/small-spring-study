package springframework.beans.factory.config;

import springframework.beans.BeansException;
import springframework.beans.factory.BeanFactory;

/**
 * Extension of the {@link BeanFactory}
 * interface to be implemented by bean factories that are capable of
 * autowiring, provided that they want to expose this functionality for
 * existing bean instances.
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * ִ�� BeanPostProcessors �ӿ�ʵ����� postProcessBeforeInitialization ����
     *
     * @param existingBean
     * @param beanName bean ����
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * ִ�� BeanPostProcessors �ӿ�ʵ����� postProcessorsAfterInitialization ����
     *
     * @param existingBean
     * @param beanName bean ����
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
