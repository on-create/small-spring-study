package springframework.beans.factory.config;

/**
 * ����ע���
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
