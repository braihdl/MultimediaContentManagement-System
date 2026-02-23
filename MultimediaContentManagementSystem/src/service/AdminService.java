package service;

import data.UserRepository;
import model.User;
import model.UserRole;

public class AdminService {

    private final UserRepository users;

    public AdminService(UserRepository users) {
        this.users = users;
    }

    public void updatePermissions(User admin, int userId, boolean up, boolean ed, boolean del) {
        if (admin == null || admin.getRole() != UserRole.ADMIN)
            throw new IllegalArgumentException("Only ADMIN can change permissions.");

        User u = users.findById(userId);
        if (u == null)
            throw new IllegalArgumentException("User not found.");

        u.setCanUpload(up);
        u.setCanEdit(ed);
        u.setCanDelete(del);
    }
}