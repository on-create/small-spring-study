package springframework.event;

import springframework.context.ApplicationListener;
import springframework.context.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("ˢ���¼���" + this.getClass().getName());
    }

}
