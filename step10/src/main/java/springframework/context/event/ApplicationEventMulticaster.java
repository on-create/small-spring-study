package springframework.context.event;

import springframework.context.ApplicationEvent;
import springframework.context.ApplicationListener;

/**
 * Interface to be implemented by objects that can manage a number of
 * {@link ApplicationListener} objects, and publish events to them.
 *
 * �¼��㲥��
 */
public interface ApplicationEventMulticaster {

    /**
     * Add a listener to be notified of all events.
     * <br/>���һ����������֪ͨ�����¼�
     * @param listener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list.
     * <br/>��֪ͨ�б���ɾ��������
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * Multicast the given application event to appropriate listeners.
     * <br/>��������Ӧ�ó����¼��ಥ���ʵ���������
     * @param event the event to multicast
     */
    void multicastEvent(ApplicationEvent event);
}
