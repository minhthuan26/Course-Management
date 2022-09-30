package QuanLiKhoaHoc.DTO;

import java.sql.Date;
import java.time.LocalDate;

public class Course {
    int CourseId;
    String CourseName, CourseDescription, CourseImage;
    LocalDate DateStart, DateEnd, DateCreate;

    public LocalDate getDateStart() {
        return DateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        DateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        DateEnd = dateEnd;
    }

    public LocalDate getDateCreate() {
        return DateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        DateCreate = dateCreate;
    }

    public Course(int courseId, String courseName, String courseDescription, LocalDate dateCreate , LocalDate dateStart, LocalDate dateEnd, String courseImage) {
        CourseId = courseId;
        CourseName = courseName;
        CourseDescription = courseDescription;
        DateStart = dateStart;
        DateEnd = dateEnd;
        DateCreate = dateCreate;
        CourseImage = courseImage;

    }


    public Course(String courseName, String courseDesc, java.util.Date dateCreate, java.util.Date dateStart, java.util.Date dateEnd, Object courseImage) {
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
