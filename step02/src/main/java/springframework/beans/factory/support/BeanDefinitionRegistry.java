package springframework.beans.factory.support;

import springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册 BeanDefinition
     * @param beanName bean 名称
     * @param beanDefinition bean 描述对象
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
