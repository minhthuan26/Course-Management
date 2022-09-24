package QuanLiKhoaHoc.DTO;

import java.sql.Date;

public class OnlineCourse{
    int OnlineCourseId, courseId;
    String CourseUrl;

    public OnlineCourse(int onlineCourseId, int courseId, String courseUrl) {
        OnlineCourseId = onlineCourseId;
        courseId = courseId;
        CourseUrl = courseUrl;
    }

    public int getOnlineCourseId() {
        return OnlineCourseId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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
