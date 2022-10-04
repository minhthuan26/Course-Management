package QuanLiKhoaHoc.DAL.AssignmentManage;

import QuanLiKhoaHoc.DAL.ConnectDB;
import QuanLiKhoaHoc.DTO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;


public class AssignmentDAL {

    ConnectDB connect = new ConnectDB();

    public ObservableList<Person> getAllTeacherList() {
        ObservableList<Person> allTeacherList = FXCollections.observableArrayList();
        String query = "Select * " +
                "from Person, PersonRole " +
                "where Person.PersonId=PersonRole.PersonId " +
                "and PersonRole.RoleId=1 ";
        try {
            ResultSet resultSet = connect.excuteQuery(query);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Person teacher = new Person(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getDate(6).toLocalDate(),
                            resultSet.getString(7)
                    );
                    allTeacherList.add(teacher);
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return allTeacherList;
    }

    public ObservableList<Person> getTeacherAssignmnentList() {
        ObservableList<Person> teacherAssignmentList = FXCollections.observableArrayList();
        String query = "Select * " +
                "from Person, PersonRole " +
                "where Person.PersonId=PersonRole.PersonId " +
                "and PersonRole.RoleId=1 " +
                "and not exists (select * from Assignment where Person.PersonId=Assignment.PersonId)";
        try {
            ResultSet resultSet = connect.excuteQuery(query);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Person teacher = new Person(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getDate(6).toLocalDate(),
                            resultSet.getString(7)
                    );
                    teacherAssignmentList.add(teacher);
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return teacherAssignmentList;
    }

    public ObservableList<OnsiteCourse> getOnsiteCourseList() {
        ObservableList<OnsiteCourse> onsiteCourseList = FXCollections.observableArrayList();
        ObservableList<Course> courseList = getAllCourseList();
        for (Course course : courseList) {
            String query = "Select * from OnsiteCourse " +
                    "where CourseId='" + course.getCourseId() + "' " +
                    "and not exists (select * from Assignment where CourseId='" + course.getCourseId() + "')";
            try {
                ResultSet resultSet = connect.excuteQuery(query);
                if (resultSet != null) {
                    while (resultSet.next()) {
                        OnsiteCourse onsiteCourse = new OnsiteCourse(
                                course.getCourseId(),
                                course.getCourseName(),
                                course.getCourseDescription(),
                                course.getDateCreate(),
                                course.getDateStart(),
                                course.getDateEnd(),
                                course.getCourseImage(),
                                resultSet.getInt(1),
                                resultSet.getInt(3),
                                resultSet.getTime(4),
                                resultSet.getTime(5),
                                resultSet.getDate(6).toLocalDate(),
                                resultSet.getInt(7)
                        );
                        onsiteCourseList.add(onsiteCourse);
                    }
                }
            } catch (Exception error) {
                error.printStackTrace();
            }
        }
        return onsiteCourseList;
    }

    public ObservableList<OnlineCourse> getOnlineCourseList() {
        ObservableList<OnlineCourse> onlineCourseList = FXCollections.observableArrayList();
        ObservableList<Course> courseList = getAllCourseList();
        for (Course course : courseList) {
            String query = "Select * from OnlineCourse " +
                    "where CourseId='" + course.getCourseId() + "' " +
                    "and not exists (select * from Assignment where CourseId='" + course.getCourseId() + "')";
            try {
                ResultSet resultSet = connect.excuteQuery(query);
                if (resultSet != null) {
                    while (resultSet.next()) {
                        OnlineCourse onlineCourse = new OnlineCourse(
                                course.getCourseId(),
                                course.getCourseName(),
                                course.getCourseDescription(),
                                course.getDateCreate(),
                                course.getDateStart(),
                                course.getDateEnd(),
                                course.getCourseImage(),
                                resultSet.getInt(1),
                                resultSet.getString(2)
                        );
                        onlineCourseList.add(onlineCourse);
                    }
                }
            } catch (Exception error) {
                error.printStackTrace();
            }
        }
        return onlineCourseList;
    }

    public ObservableList<Course> getAllCourseList() {
        ObservableList<Course> CourseList = FXCollections.observableArrayList();

        String query = "Select * from Course";
        try {
            ResultSet resultSet = connect.excuteQuery(query);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Course course = new Course(
                            resultSet.getInt("CourseID"),
                            resultSet.getString("CourseName"),
                            resultSet.getString("CourseDescription"),
                            resultSet.getDate("DateEnd").toLocalDate(),
                            resultSet.getDate("DateCreate").toLocalDate(),
                            resultSet.getDate("DateStart").toLocalDate(),
                            resultSet.getString("CourseImage")
                    );
                    CourseList.add(course);
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return CourseList;
    }

    public Assignment setAssignment(Assignment assignment) {
        int result = 0;
        String query = "insert into Assignment values('" + assignment.getCourseId() + "', '" + assignment.getPersonId() + "')";
        try {
            result = connect.ExecuteUpdate(query);
            if (result == 0) {
                return null;
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return assignment;
    }

    public ObservableList<Assignment> getAssignmentList() {
        ObservableList<Assignment> assignmentList = FXCollections.observableArrayList();
        String query = "select * from Assignment";
        try {
            ResultSet resultSet = connect.excuteQuery(query);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Assignment assignment = new Assignment(
                            resultSet.getInt(1),
                            resultSet.getInt(2)
                    );
                    assignmentList.add(assignment);
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return assignmentList;
    }

    public Assignment deleteAssignment(Assignment assignment){
        int result = 0;
        String query = "delete from Assignment " +
                "where CourseId='" + assignment.getCourseId() +
                "' and PersonId='" + assignment.getPersonId() +"'";
        try{
            result = connect.ExecuteUpdate(query);
            if(result == 0)
                return null;
        }
        catch(Exception error){
            error.printStackTrace();
        }
        return assignment;
    }

    public Course getSearchResultByCourse(int courseId){
        String query = "select * from Course where CourseId=" + courseId;
        try{
            ResultSet resultSet = connect.excuteQuery(query);
            while(resultSet.next()){
                return new Course(
                        resultSet.getInt("CourseID"),
                        resultSet.getString("CourseName"),
                        resultSet.getString("CourseDescription"),
                        resultSet.getDate("DateEnd").toLocalDate(),
                        resultSet.getDate("DateCreate").toLocalDate(),
                        resultSet.getDate("DateStart").toLocalDate(),
                        resultSet.getString("CourseImage")
                );
            }
        }
        catch (Exception error){
            error.printStackTrace();
        }
        return null;
    }

    public Person getResultByTeacherId(int studentId){
        String query = "select * from Person " +
                "where PersonId=" + studentId;
        try{
            ResultSet resultSet = connect.excuteQuery(query);
            while(resultSet.next()){
                return new Person(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6).toLocalDate(),
                        resultSet.getString(7)
                );
            }
        }
        catch (Exception error){
            error.printStackTrace();
        }
        return null;
    }
}
