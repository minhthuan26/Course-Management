package QuanLiKhoaHoc.DTO;

public class PersonRole {
    int RoleId, PersonId;

    public PersonRole(int roleId, int personId) {
        RoleId = roleId;
        PersonId = personId;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public int getPersonId() {
        return PersonId;
    }

    public void setPersonId(int personId) {
        PersonId = personId;
    }

    @Override
    public String toString() {
        return "PersonRole{" +
                "RoleId=" + RoleId +
                ", PersonId=" + PersonId +
                '}';
    }
}
