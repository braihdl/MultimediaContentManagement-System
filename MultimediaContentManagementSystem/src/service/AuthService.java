package service;

import data.UserRepository;
import model.User;

public class AuthService {

    private final UserRepository users;

    public AuthService(UserRepository users) {
        this.users = users;
    }

    public User login(String username, String password) {
        User u = users.findByUsername(username);
        if (u == null) return null;
        if (!u.getPassword().equals(password)) return null;
        return u;
    }
}