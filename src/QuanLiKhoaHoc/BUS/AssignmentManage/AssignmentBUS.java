package QuanLiKhoaHoc.BUS.AssignmentManage;

import QuanLiKhoaHoc.DAL.AssignmentManage.AssignmentDAL;
import QuanLiKhoaHoc.DTO.*;
import javafx.collections.ObservableList;

public class AssignmentBUS {
    public AssignmentDAL assignmentDAL = new AssignmentDAL();
    public static ObservableList<Course> allCourseList;
    public static ObservableList<OnsiteCourse> onsiteCourseList;
    public static ObservableList<OnlineCourse> onlineCourseList;
    public static ObservableList<Person> teacherAssignmentList;
    public static ObservableList<Person> allTeacherList;
    public static ObservableList<Assignment> assignmentList;

    public ObservableList<Course> getAllCourseList(){
        return allCourseList = assignmentDAL.getAllCourseList();
    }

    public ObservableList<Person> getTeacherAssignmentList(){
        return teacherAssignmentList = assignmentDAL.getTeacherAssignmnentList();
    }

    public ObservableList<Person> getAllTeacherList(){
        return allTeacherList = assignmentDAL.getAllTeacherList();
    }

    public ObservableList<OnlineCourse> getOnlineCourseList(){
        return onlineCourseList = assignmentDAL.getOnlineCourseList();
    }

    public ObservableList<OnsiteCourse> getOnsiteCourseList(){
        return onsiteCourseList = assignmentDAL.getOnsiteCourseList();
    }

    public ObservableList<Assignment> getAssignmentList(){
        return assignmentList = assignmentDAL.getAssignmentList();
    }

    public Assignment setAssignment(Assignment assignment){
        return assignmentDAL.setAssignment(assignment);
    }

    public Assignment deleteAssignment(Assignment assignment){
        return assignmentDAL.deleteAssignment(assignment);
    }
}
