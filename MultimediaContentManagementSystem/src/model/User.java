package model;

public class User {

    private int id;
    private String username;
    private String password;
    private UserRole role;

    private boolean canUpload;
    private boolean canEdit;
    private boolean canDelete;

    public User(int id, String username, String password, UserRole role,
                boolean canUpload, boolean canEdit, boolean canDelete) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.canUpload = canUpload;
        this.canEdit = canEdit;
        this.canDelete = canDelete;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public UserRole getRole() { return role; }

    public boolean canUpload() { return canUpload; }
    public boolean canEdit() { return canEdit; }
    public boolean canDelete() { return canDelete; }

    public void setCanUpload(boolean canUpload) { this.canUpload = canUpload; }
    public void setCanEdit(boolean canEdit) { this.canEdit = canEdit; }
    public void setCanDelete(boolean canDelete) { this.canDelete = canDelete; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", canUpload=" + canUpload +
                ", canEdit=" + canEdit +
                ", canDelete=" + canDelete +
                '}';
    }
}