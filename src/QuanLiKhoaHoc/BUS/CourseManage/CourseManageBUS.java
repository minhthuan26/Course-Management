package QuanLiKhoaHoc.BUS.CourseManage;

import QuanLiKhoaHoc.DAL.CourseManageDAL;
import QuanLiKhoaHoc.DTO.Course;
import QuanLiKhoaHoc.DTO.OnlineCourse;
import QuanLiKhoaHoc.DTO.OnsiteCourse;
import javafx.collections.ObservableList;

public class CourseManageBUS {
    CourseManageDAL courseManageDAL = new CourseManageDAL();
    public static ObservableList<Course> allCourseList;
    public static ObservableList<OnlineCourse> allOnlineCourseList;
    public static ObservableList<OnsiteCourse> allOnsiteCourseList;

    public ObservableList<Course> getAllCourseList() {
        return allCourseList =  courseManageDAL.getCourseList();
    }
    public ObservableList<OnlineCourse> getAllOnlineCourseList(){
        return allOnlineCourseList = courseManageDAL.getOnlineCourseList();
    }
    public ObservableList<OnsiteCourse> getAllOnsiteCourseList(){
        return allOnsiteCourseList = courseManageDAL.getOnsiteCourseList();
    }
}
