package springframework.beans.factory.support;

import springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    /**
     * ��ע�����ע�� BeanDefinition
     * @param beanName bean ����
     * @param beanDefinition bean ��������
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
