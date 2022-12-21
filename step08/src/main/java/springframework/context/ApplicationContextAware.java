package springframework.context;

import springframework.beans.BeansException;
import springframework.beans.factory.Aware;

/**
 * Interface to be implemented by any object that wishes to be notified
 * of the {@link ApplicationContext} that it runs in.
 * <br/>
 * ʵ�ִ˽ӿڣ����ܸ�֪�������� ApplicationContext
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
