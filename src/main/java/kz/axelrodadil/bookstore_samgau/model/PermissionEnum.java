package kz.axelrodadil.bookstore_samgau.model;

public enum PermissionEnum {

    Scheduler_READ("scheduler:read"),
    Scheduler_WRITE("scheduler:write");

    private final String permission;

    PermissionEnum(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
