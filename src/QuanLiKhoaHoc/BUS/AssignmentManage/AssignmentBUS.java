package QuanLiKhoaHoc.BUS.AssignmentManage;

import QuanLiKhoaHoc.DAL.AssignmentManage.AssignmentDAL;
import QuanLiKhoaHoc.DTO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.Map;

public class AssignmentBUS {
    public AssignmentDAL assignmentDAL = new AssignmentDAL();
    private ObservableList<Course> allCourseList;
    private ObservableList<OnsiteCourse> onsiteCourseList;
    private ObservableList<OnlineCourse> onlineCourseList;
    private ObservableList<Person> teacherAssignmentList;
    private ObservableList<Person> allTeacherList;

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
        return assignmentDAL.getAssignmentList();
    }

    public Assignment setAssignment(Assignment assignment){
        return assignmentDAL.setAssignment(assignment);
    }

    public Assignment deleteAssignment(Assignment assignment){
        return assignmentDAL.deleteAssignment(assignment);
    }

    public ObservableList<String> getOnsiteList(){
        allCourseList = getAllCourseList();
        onsiteCourseList = getOnsiteCourseList();
        ObservableMap<Integer, String> onsiteCourseNameAndIdList = FXCollections.observableHashMap();
        for(OnsiteCourse onsiteCourse : onsiteCourseList){
            for(Course course : allCourseList)
                if(course.getCourseId() == onsiteCourse.getCourseId()){
                    onsiteCourseNameAndIdList.put(course.getCourseId(), course.getCourseName());
                    break;
                }
        }
        //
        ObservableList<String> onsiteCourseListToGUI = FXCollections.observableArrayList();
        for(Map.Entry<Integer, String> onsiteCourse : onsiteCourseNameAndIdList.entrySet()){
            onsiteCourseListToGUI.add(onsiteCourse.getKey() + "_" + onsiteCourse.getValue());
        }
        return onsiteCourseListToGUI;
    }

    public ObservableList<String> getOnlineList(){
        allCourseList = getAllCourseList();
        onlineCourseList = getOnlineCourseList();
        ObservableMap<Integer, String> onlineCourseNameAndIdList = FXCollections.observableHashMap();
        for(OnlineCourse onlineCourse : onlineCourseList){
            for(Course course : allCourseList)
                if(course.getCourseId() == onlineCourse.getCourseId()){
                    onlineCourseNameAndIdList.put(course.getCourseId(), course.getCourseName());
                    break;
                }
        }
        ObservableList<String> onlineCourseListToGUI = FXCollections.observableArrayList();
        for(Map.Entry<Integer, String> onlineCourse : onlineCourseNameAndIdList.entrySet()){
            onlineCourseListToGUI.add(onlineCourse.getKey() + "_" + onlineCourse.getValue());
        }
        return onlineCourseListToGUI;
    }

    public ObservableMap<Integer, String> getAllCourseListToGUI(){
        allCourseList = getAllCourseList();
        ObservableMap<Integer, String> allCourseListToGUI = FXCollections.observableHashMap();
        for(Course course : allCourseList)
            allCourseListToGUI.put(course.getCourseId(), course.getCourseName());
        return allCourseListToGUI;
    }

    public ObservableMap<Integer, String> getAllTeacherNameAndIdListToGUI(){
        allTeacherList = getAllTeacherList();
        ObservableMap<Integer, String> allTeacherNameAndIdList = FXCollections.observableHashMap();
        for(Person person : allTeacherList)
            allTeacherNameAndIdList.put(person.getPersonId(), String.join(" ", person.getFirstName(), person.getLastName()));
        return allTeacherNameAndIdList;
    }

    public ObservableList<String> getTeacherAssignmentListToGUI(){
        teacherAssignmentList = getTeacherAssignmentList();
        ObservableMap<Integer, String> teacherAssignmentNameAndIdList = FXCollections.observableHashMap();
        for(Person person : teacherAssignmentList){
            teacherAssignmentNameAndIdList.put(person.getPersonId(), String.join(" ", person.getFirstName(), person.getLastName()));
        }
        ObservableList<String> teacherNameList = FXCollections.observableArrayList();
        for(Map.Entry<Integer, String> teacher : teacherAssignmentNameAndIdList.entrySet()){
            teacherNameList.add(teacher.getKey() + "_" + teacher.getValue());
        }
        return teacherNameList;
    }

}
