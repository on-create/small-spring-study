package springframework.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("10001", "����");
        map.put("10002", "����");
        map.put("10003", "����");
    }

    public String queryUserName(String userId) {
        return map.get(userId);
    }
}
