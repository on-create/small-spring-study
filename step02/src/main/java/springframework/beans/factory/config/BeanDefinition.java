package springframework.beans.factory.config;

// rawtypes: 告诉编译器不用提示使用基本类型参数时相关的警告信息
@SuppressWarnings(value = {"rawtypes"})
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
