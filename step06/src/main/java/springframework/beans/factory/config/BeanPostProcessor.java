package springframework.beans.factory.config;

import springframework.beans.BeansException;

/**
 * Factory hook that allows for custom modification of new bean instances,
 * e.g. checking for marker interfaces or wrapping them with proxies.
 *
 * �����޸���ʵ���� Bean �������չ��
 */
public interface BeanPostProcessor {

    /**
     * �� Bean ����ִ�г�ʼ������֮ǰ��ִ�д˷���
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * �� Bean ����ִ�г�ʼ������֮��ִ�д˷���
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
