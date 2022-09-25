package QuanLiKhoaHoc.DTO;

public class OnlineCourse extends Course{
    int OnlineCourseId, CourseId;
    String CourseUrl;


    public OnlineCourse(int courseId, String courseName, String courseDescription,Date dateCreate , Date dateStart, Date dateEnd,String courseImage,int onlineCourseId, String courseUrl) {
        super(courseId, courseName, courseDescription,dateCreate , dateStart, dateEnd,courseImage );

        OnlineCourseId = onlineCourseId;
        CourseId = courseId;
        CourseUrl = courseUrl;
    }

    public int getOnlineCourseId() {
        return OnlineCourseId;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int courseId) {
        this.CourseId = courseId;
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
