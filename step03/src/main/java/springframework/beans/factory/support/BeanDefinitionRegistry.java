package springframework.beans.factory.support;

import springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    /**
     * Ïò×¢²á±íÖÐ×¢²á BeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
