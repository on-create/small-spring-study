package springframework.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static Map<String, String> map = new HashMap<>();

    public void initDataMethod() {
        System.out.println("ִ��: init-method");
        map.put("10001", "����");
        map.put("10002", "����");
        map.put("10003", "����");
    }

    public void destroyDataMethod() {
        System.out.println("ִ��: destroy-method");
        map.clear();
    }

    public String queryUserName(String userId) {
        return map.get(userId);
    }
}
