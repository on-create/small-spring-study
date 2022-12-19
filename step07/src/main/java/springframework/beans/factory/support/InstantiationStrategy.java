package springframework.beans.factory.support;

import springframework.beans.BeansException;
import springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * bean ÊµÀý»¯²ßÂÔ
 */
public interface InstantiationStrategy {

    Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor constructor, Object[] args) throws BeansException;
}
