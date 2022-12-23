package springframework.context.event;

import springframework.context.ApplicationEvent;
import springframework.context.ApplicationListener;

/**
 * Interface to be implemented by objects that can manage a number of
 * {@link ApplicationListener} objects, and publish events to them.
 *
 * 事件广播器
 */
public interface ApplicationEventMulticaster {

    /**
     * Add a listener to be notified of all events.
     * <br/>添加一个侦听器来通知所有事件
     * @param listener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list.
     * <br/>从通知列表中删除侦听器
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * Multicast the given application event to appropriate listeners.
     * <br/>将给定的应用程序事件多播到适当的侦听器
     * @param event the event to multicast
     */
    void multicastEvent(ApplicationEvent event);
}
