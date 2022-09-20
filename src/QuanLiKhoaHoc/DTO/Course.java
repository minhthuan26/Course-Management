package QuanLiKhoaHoc.DTO;

import java.sql.Date;

public class Course {
    int CourseId;
    String CourseName, CourseDescription, CourseImage;
    Date DateStart, DateEnd, DateCreate;

    public Course(int courseId, String courseName, String courseDescription, String courseImage, Date dateStart, Date dateEnd, Date dateCreate) {
        CourseId = courseId;
        CourseName = courseName;
        CourseDescription = courseDescription;
        CourseImage = courseImage;
        DateStart = dateStart;
        DateEnd = dateEnd;
        DateCreate = dateCreate;
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

    public String getCourseImage() {
        return CourseImage;
    }

    public void setCourseImage(String courseImage) {
        CourseImage = courseImage;
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

    @Override
    public String toString() {
        return "Course{" +
                "CourseId=" + CourseId +
                ", CourseName='" + CourseName + '\'' +
                ", CourseDescription='" + CourseDescription + '\'' +
                ", CourseImage='" + CourseImage + '\'' +
                ", DateStart=" + DateStart +
                ", DateEnd=" + DateEnd +
                ", DateCreate=" + DateCreate +
                '}';
    }
}
