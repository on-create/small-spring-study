package springframework.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("10001", "张三");
        map.put("10002", "李四");
        map.put("10003", "王五");
    }

    public String queryUserName(String userId) {
        return map.get(userId);
    }
}
