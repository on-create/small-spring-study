package springframework.beans.factory.support;

import springframework.beans.BeansException;
import springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy strategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    private Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Constructor constructorToUse = null;
        Class beanClass = beanDefinition.getBeanClass();
        for (Constructor declaredConstructor : beanClass.getDeclaredConstructors()) {
            Class[] types = declaredConstructor.getParameterTypes();
            // 参数类型是否相同 标志位
            boolean flag = true;
            if (null != args && types.length == args.length) {
                // 比较入参类型
                for (int i = 0; i < types.length; i++) {
                    if (types[i] != args[i].getClass()) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    constructorToUse = declaredConstructor;
                    break;
                }
            }
        }
        return getStrategy().instantiate(beanName, beanDefinition, constructorToUse, args);
    }

    public InstantiationStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(InstantiationStrategy strategy) {
        this.strategy = strategy;
    }
}
