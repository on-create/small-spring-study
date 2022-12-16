package springframework.beans.factory.support;

import springframework.beans.BeansException;
import springframework.core.io.Resource;
import springframework.core.io.ResourceLoader;

public interface BeanDefinitionReader {

    // getRegistry �� getResourceLoader �ṩ������������ʹ��
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
