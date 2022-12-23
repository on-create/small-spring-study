package springframework.beans.factory.config;

/**
 * µ¥Àý×¢²á±í
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);
}
