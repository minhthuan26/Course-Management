package QuanLiKhoaHoc.DTO;

import java.sql.Date;

public class OnlineCourse extends Course{
    int OnlineCourseId;
    String CourseUrl;

    public OnlineCourse(int courseId, String courseName, String courseDescription, String courseImage, Date dateStart, Date dateEnd, Date dateCreate, int onlineCourseId, String courseUrl) {
        super(courseId, courseName, courseDescription, courseImage, dateStart, dateEnd, dateCreate);
        OnlineCourseId = onlineCourseId;
        CourseUrl = courseUrl;
    }

    public int getOnlineCourseId() {
        return OnlineCourseId;
    }

    public void setOnlineCourseId(int onlineCourseId) {
        OnlineCourseId = onlineCourseId;
    }

    public String getCourseUrl() {
        return CourseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        CourseUrl = courseUrl;
    }

    @Override
    public String toString() {
        return "OnlineCourse{" +
                "OnlineCourseId=" + OnlineCourseId +
                ", CourseUrl='" + CourseUrl + '\'' +
                '}';
    }
}
