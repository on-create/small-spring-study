package springframework.event;

import springframework.context.ApplicationListener;

import java.util.Date;

public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("�յ���" + event.getSource() + "��Ϣ;ʱ�䣺" + new Date());
        System.out.println("��Ϣ��" + event.getId() + ":" + event.getMessage());
    }
}
