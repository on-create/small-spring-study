package springframework.beans.factory;

import springframework.beans.BeansException;

/**
 * Interface to be implemented by beans that wish to be aware of their
 * owning {@link BeanFactory}.
 *
 * ʵ�ִ˽ӿڣ����ܸ�֪�������� BeanFactory
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
