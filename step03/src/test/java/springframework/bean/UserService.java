package springframework.bean;

public class UserService {

    private String name;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("��ѯ�û���Ϣ��" + name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("name: ").append(name);
        return builder.toString();
    }
}
