package springframework.context;

import springframework.beans.BeansException;

/**SPI interface to be implemented by most if not all application contexts.
 * Provides facilities to configure an application context in addition
 * to the application context client methods in the
 * {@link springframework.context.ApplicationContext} interface.
 *
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * ˢ������
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
