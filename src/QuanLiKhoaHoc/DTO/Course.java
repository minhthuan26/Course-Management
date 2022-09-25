package QuanLiKhoaHoc.DTO;

import java.sql.Date;

public class Course {
    int CourseId;
    String CourseName, CourseDescription, CourseImage;
    Date DateStart, DateEnd, DateCreate;

    public Course(int courseId, String courseName, String courseDescription,Date dateCreate , Date dateStart, Date dateEnd,String courseImage) {
        CourseId = courseId;
        CourseName = courseName;
        CourseDescription = courseDescription;
        DateStart = dateStart;
        DateEnd = dateEnd;
        DateCreate = dateCreate;
        CourseImage = courseImage;

    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int courseId) {
        CourseId = courseId;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCourseDescription() {
        return CourseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        CourseDescription = courseDescription;
    }

    public Date getDateStart() {
        return DateStart;
    }

    public void setDateStart(Date dateStart) {
        DateStart = dateStart;
    }

    public Date getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        DateEnd = dateEnd;
    }

    public Date getDateCreate() {
        return DateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        DateCreate = dateCreate;
    }

    public String getCourseImage() {
        return CourseImage;
    }

    public void setCourseImage(String courseImage) {
        CourseImage = courseImage;
    }

    @Override
    public String toString() {
        return "Course{" +
                "CourseId=" + CourseId +
                ", CourseName='" + CourseName + '\'' +
                ", CourseDescription='" + CourseDescription + '\'' +
                ", DateStart=" + DateStart +
                ", DateEnd=" + DateEnd +
                ", DateCreate=" + DateCreate +
                ", CourseImage='" + CourseImage + '\'' +
                '}';
    }
}
