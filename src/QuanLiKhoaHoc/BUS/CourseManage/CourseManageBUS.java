package QuanLiKhoaHoc.BUS.CourseManage;

import QuanLiKhoaHoc.DAL.CourseManage.CourseManageDAL;
import QuanLiKhoaHoc.DTO.Assignment;
import QuanLiKhoaHoc.DTO.Course;
import QuanLiKhoaHoc.DTO.OnlineCourse;
import QuanLiKhoaHoc.DTO.OnsiteCourse;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class CourseManageBUS {
    CourseManageDAL courseManageDAL = new CourseManageDAL();
    public static ObservableList<Course> allCourseList;
    public static ObservableList<OnlineCourse> allOnlineCourseList;
    public static ObservableList<OnsiteCourse> allOnsiteCourseList;

    public ObservableList<Course> getAllCourseList() {
        return allCourseList = courseManageDAL.getCourseList();
    }

    public ObservableList<OnlineCourse> getAllOnlineCourseList() {
        return allOnlineCourseList= courseManageDAL.getOnlineCourseList();
    }

    public ObservableList<OnsiteCourse> getAllOnsiteCourseList() {
        return allOnsiteCourseList = courseManageDAL.getOnsiteCourseList();
    }
    public Course deleteOnlineCourseTest(int courseId){
        return courseManageDAL.deleteOnlineCourseTest(courseId);
    }
    public Course deleteOnsiteCourseTest(int courseId){
        return courseManageDAL.deleteOnsiteCourseTest(courseId);
    }

    public Course deleteCourseOnsite(Course courseId){
        return courseManageDAL.deleteCourseOnsite(courseId);
    }
    public Course deleteCourseOnline(Course course){
        return courseManageDAL.deleteCourseOnline(course);
    }
    public Assignment getIdAssignment(int id){
        return courseManageDAL.getIdAssignment(id);
    }

    public Course addCourse(String name, String desc, LocalDate cre, LocalDate start, LocalDate end){
        return courseManageDAL.addCourse(name , desc, cre, start, end);
    }
    public Course addOnlineCourse(int id, String url){
        return courseManageDAL.addOnlineCourse(id, url);
    }
    public  Course addOnsiteCourse(int id, int less, LocalDate occur){return  courseManageDAL.addOnsiteCourse(id, less, occur);}
}
