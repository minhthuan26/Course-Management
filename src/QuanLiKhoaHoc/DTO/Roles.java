package QuanLiKhoaHoc.DTO;

public class Roles {
    int RoleId;
    String RoleName;

    public Roles(int roleId, String roleName) {
        RoleId = roleId;
        RoleName = roleName;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "RoleId=" + RoleId +
                ", RoleName='" + RoleName + '\'' +
                '}';
    }
}
