package QuanLiKhoaHoc.DTO;

public class Assignment {
    int CourseId, PersonId;

    public Assignment(int courseId, int personId) {
        CourseId = courseId;
        PersonId = personId;
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
        return "Assignment{" +
                "CourseId=" + CourseId +
                ", PersonId=" + PersonId +
                '}';
    }
}
