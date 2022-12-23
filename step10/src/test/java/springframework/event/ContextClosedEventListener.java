package springframework.event;

import springframework.context.ApplicationListener;
import springframework.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("�ر��¼���" + this.getClass().getName());
    }
}
