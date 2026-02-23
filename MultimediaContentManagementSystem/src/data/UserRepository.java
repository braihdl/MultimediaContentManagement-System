package data;

import model.User;
import model.UserRole;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(1, "admin", "1234", UserRole.ADMIN, true, true, true));
        users.add(new User(2, "creator", "1234", UserRole.CREATOR, true, true, false));
        users.add(new User(3, "user", "1234", UserRole.END_USER, false, false, false));
    }

    public User findByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }

    public User findById(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public List<User> getAll() {
        return users;
    }
}