package QuanLiKhoaHoc.DTO;

public class CourseRegister {
    int RegisterId, CourseId, PersonId;

    public CourseRegister(int registerId, int courseId, int personId) {
        RegisterId = registerId;
        CourseId = courseId;
        PersonId = personId;
    }

    public int getRegisterId() {
        return RegisterId;
    }

    public void setRegisterId(int registerId) {
        RegisterId = registerId;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int courseId) {
        CourseId = courseId;
    }

    public int getPersonId() {
        return PersonId;
    }

    public void setPersonId(int personId) {
        PersonId = personId;
    }

    @Override
    public String toString() {
        return "CourseRegister{" +
                "RegisterId=" + RegisterId +
                ", CourseId=" + CourseId +
                ", PersonId=" + PersonId +
                '}';
    }
}
