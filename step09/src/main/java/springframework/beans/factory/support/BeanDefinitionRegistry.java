package springframework.beans.factory.support;

import springframework.beans.BeansException;
import springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    /**
     * ��ע�����ע�� BeanDefinition
     *
     * @param beanName bean ����
     * @param beanDefinition beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * ʹ��Bean���Ʋ�ѯBeanDefinition
     *
     * @param beanName bean ����
     * @return BeanDefinition
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * �ж��Ƿ����ָ�����Ƶ�BeanDefinition
     * @param beanName bean ����
     * @return ������ڶ�Ӧ���Ƶ� bean ���󣬷���true�����򷵻� false
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * Return the names of all beans defined in this registry.
     *
     * ����ע��������е�Bean����
     */
    String[] getBeanDefinitionNames();
}
