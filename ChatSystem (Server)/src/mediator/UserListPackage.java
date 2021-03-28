package mediator;

import java.util.ArrayList;

public class UserListPackage {
    private String type;
    private ArrayList<String> users;

    public UserListPackage(String type) {
        this(type, new ArrayList<>());
    }

    public UserListPackage(String type, ArrayList<String> users) {
        this.type = type;
        this.users = users;
    }

    public String getType() {
        return type;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Package{" +
                type + ", " +
                users +
                '}';
    }

}
