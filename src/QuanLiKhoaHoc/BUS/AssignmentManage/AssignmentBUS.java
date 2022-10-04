package QuanLiKhoaHoc.BUS.AssignmentManage;

import QuanLiKhoaHoc.BUS.ResultManage.ResultBUS;
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
        getAllCourseList();
        getOnsiteCourseList();
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
        getAllCourseList();
        getOnlineCourseList();
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

    public ObservableMap<Integer, String> getAllCourseNameAndIdList(){
        getAllCourseList();
        ObservableMap<Integer, String> allCourseListToGUI = FXCollections.observableHashMap();
        for(Course course : allCourseList)
            allCourseListToGUI.put(course.getCourseId(), course.getCourseName());
        return allCourseListToGUI;
    }

    public ObservableList<String> getAllCourseListToGUI() {
        ObservableMap<Integer, String> allCourseNameAndIdList = getAllCourseNameAndIdList();
        ObservableList<String> allCourseNameListToGUI = FXCollections.observableArrayList();
        for (Map.Entry<Integer, String> teacher : allCourseNameAndIdList.entrySet()) {
            allCourseNameListToGUI.add(teacher.getKey() + "_" + teacher.getValue());
        }
        return allCourseNameListToGUI;
    }

    public ObservableMap<Integer, String> getAllTeacherNameAndIdList(){
        getAllTeacherList();
        ObservableMap<Integer, String> allTeacherNameAndIdList = FXCollections.observableHashMap();
        for(Person person : allTeacherList)
            allTeacherNameAndIdList.put(person.getPersonId(), String.join(" ", person.getLastName(), person.getFirstName()));
        return allTeacherNameAndIdList;
    }

    public ObservableList<String> getTeacherAssignmentListToGUI(){
        getTeacherAssignmentList();
        ObservableMap<Integer, String> teacherAssignmentNameAndIdList = FXCollections.observableHashMap();
        for(Person person : teacherAssignmentList){
            teacherAssignmentNameAndIdList.put(person.getPersonId(), String.join(" ", person.getLastName(), person.getFirstName()));
        }
        ObservableList<String> teacherNameList = FXCollections.observableArrayList();
        for(Map.Entry<Integer, String> teacher : teacherAssignmentNameAndIdList.entrySet()){
            teacherNameList.add(teacher.getKey() + "_" + teacher.getValue());
        }
        return teacherNameList;
    }

    public ObservableList<AssignmentTableView> getAssignmentTableViewList(){
        ObservableList<Assignment> assignmentList = getAssignmentList();
        ObservableList<AssignmentTableView> assignmentTableViewList = FXCollections.observableArrayList();
        ObservableMap<Integer, String> allCourseList = getAllCourseNameAndIdList();
        ObservableMap<Integer, String> allteacherList = getAllTeacherNameAndIdList();

        for(Assignment assignment : assignmentList){
            AssignmentTableView assignmentTableView = new AssignmentTableView();
            for(Map.Entry<Integer, String> course : allCourseList.entrySet()){
                if(assignment.getCourseId() == course.getKey()){
                    assignmentTableView.setCourseId(course.getKey());
                    assignmentTableView.setCourseName(course.getValue());
                    allCourseList.remove(course.getKey());
                    break;
                }
            }
            for(Map.Entry<Integer, String> teacher : allteacherList.entrySet()){
                if(assignment.getPersonId() == teacher.getKey()){
                    assignmentTableView.setPersonId(teacher.getKey());
                    assignmentTableView.setPersonName(teacher.getValue());
                    allteacherList.remove(teacher.getKey());
                    break;
                }
            }
            assignmentTableViewList.add(assignmentTableView);
        }
        return assignmentTableViewList;
    }

    public ObservableList<String> getAllTeacherListToGUI() {
        ObservableMap<Integer, String> allStudentNameAndIdList = getAllTeacherNameAndIdList();
        ObservableList<String> allStudentNameListToGUI = FXCollections.observableArrayList();
        for (Map.Entry<Integer, String> teacher : allStudentNameAndIdList.entrySet()) {
            allStudentNameListToGUI.add(teacher.getKey() + "_" + teacher.getValue());
        }
        return allStudentNameListToGUI;
    }

    public ObservableList<AssignmentTableView> getSearchResultByCourse(int searchId) {
        ObservableList<Assignment> allAssignmentList = getAssignmentList();
        ObservableList<AssignmentTableView> resultTableViewList = FXCollections.observableArrayList();
        ObservableMap<Integer, String> allTeacherList = getAllTeacherNameAndIdList();
        Course course = assignmentDAL.getSearchResultByCourse(searchId);

        for (Assignment assignment : allAssignmentList) {
            if(assignment.getCourseId() == searchId){
                AssignmentTableView resultTableView = new AssignmentTableView();
                resultTableView.setCourseId(course.getCourseId());
                resultTableView.setCourseName(course.getCourseName());

                for (Map.Entry<Integer, String> student : allTeacherList.entrySet()) {
                    if (student.getKey() == assignment.getPersonId()) {
                        resultTableView.setPersonId(student.getKey());
                        resultTableView.setPersonName(student.getValue());
                    }
                }
                resultTableViewList.add(resultTableView);
            }
        }
        return resultTableViewList;
    }

    public ObservableList<AssignmentTableView> getSearchResultByTeacher(int searchId) {
        ObservableList<Assignment> allAssignmentList = getAssignmentList();
        ObservableList<AssignmentTableView> resultTableViewList = FXCollections.observableArrayList();
        ObservableMap<Integer, String> allCourseList = getAllCourseNameAndIdList();
        Person teacher = assignmentDAL.getResultByTeacherId(searchId);

        for (Assignment assignment : allAssignmentList) {
            if(assignment.getPersonId() == searchId){
                AssignmentTableView resultTableView = new AssignmentTableView();
                resultTableView.setPersonId(teacher.getPersonId());
                resultTableView.setPersonName(teacher.getLastName() + " " + teacher.getFirstName());

                for (Map.Entry<Integer, String> course : allCourseList.entrySet()) {
                    if (course.getKey() == assignment.getCourseId()) {
                        resultTableView.setCourseId(course.getKey());
                        resultTableView.setCourseName(course.getValue());
                    }
                }
                resultTableViewList.add(resultTableView);
            }
        }
        return resultTableViewList;
    }

    public static class AssignmentTableView{
        private int CourseId, PersonId;
        private String CourseName, PersonName;

        public int getCourseId() {
            return CourseId;
        }

        public void setCourseId(int courseId) {
            CourseId = courseId;
        }

        public int getPersonId() {
            return PersonId;
        }

        public void setPersonId(int personId) {
            PersonId = personId;
        }

        public String getCourseName() {
            return CourseName;
        }

        public void setCourseName(String courseName) {
            CourseName = courseName;
        }

        public String getPersonName() {
            return PersonName;
        }

        public void setPersonName(String personName) {
            PersonName = personName;
        }

        @Override
        public String toString() {
            return "AssignmentTableView{" +
                    "CourseId=" + CourseId +
                    ", PersonId=" + PersonId +
                    ", CourseName='" + CourseName + '\'' +
                    ", PersonName='" + PersonName + '\'' +
                    '}';
        }
    }
}
