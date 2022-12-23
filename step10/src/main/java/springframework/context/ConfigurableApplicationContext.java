package springframework.context;

import springframework.beans.BeansException;

/**SPI interface to be implemented by most if not all application contexts.
 * Provides facilities to configure an application context in addition
 * to the application context client methods in the
 * {@link ApplicationContext} interface.
 *
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * Ë¢ÐÂÈÝÆ÷
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
