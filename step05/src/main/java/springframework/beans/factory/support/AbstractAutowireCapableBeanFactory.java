package springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import springframework.beans.BeansException;
import springframework.beans.PropertyValue;
import springframework.beans.PropertyValues;
import springframework.beans.factory.config.BeanDefinition;
import springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy strategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
            // �� bean �������
            applyPropertyValues(beanName, bean, beanDefinition);
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
            // ���������Ƿ���ͬ ��־λ
            boolean flag = true;
            if (null != args && types.length == args.length) {
                // �Ƚ��������
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

    /**
     * bean �������
     */
    private void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // A ���� B ��ȡ B ��ʵ������
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // �������
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values��" + beanName);
        }
    }

    public InstantiationStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(InstantiationStrategy strategy) {
        this.strategy = strategy;
    }
}
