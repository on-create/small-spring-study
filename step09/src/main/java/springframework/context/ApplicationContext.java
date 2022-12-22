package springframework.context;

import springframework.beans.factory.ListableBeanFactory;

/**
 * Central interface to provide configuration for an application.
 * This is read-only while the application is running, but may be
 * reloaded if the implementation supports this.
 *
 * Ӧ��������
 */
public interface ApplicationContext extends ListableBeanFactory {
}
