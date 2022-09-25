package QuanLiKhoaHoc.DAL.AssignmentManage;

import QuanLiKhoaHoc.DAL.ConnectDB;
import QuanLiKhoaHoc.DTO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AssignmentDAL {

    ConnectDB connect = new ConnectDB();

    public ObservableList<Person> getTeacherList(){
        ObservableList<Person> teacherList = FXCollections.observableArrayList();
        String query = "Select * " +
                "from Person, PersonRole " +
                "where Person.PersonId=PersonRole.PersonId " +
                "and PersonRole.RoleId=1";
        try{
            ResultSet resultSet = connect.excuteQuery(query);
            if(resultSet != null){
                while(resultSet.next()){
                    Person teacher = new Person(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getDate(6),
                            resultSet.getString(7)
                    );
                    teacherList.add(teacher);
                }
            }
        }
        catch(Exception error){
            error.printStackTrace();
        }
        return teacherList;
    }

    public ObservableList<OnsiteCourse> getOnsiteCourseList(){
        ObservableList<OnsiteCourse> onsiteCourseList = FXCollections.observableArrayList();

        String query = "Select * from OnsiteCourse";
        try{
            ResultSet resultSet = connect.excuteQuery(query);
            if(resultSet != null){
                while(resultSet.next()){
                    OnsiteCourse course = new OnsiteCourse(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getInt(3),
                            resultSet.getTime(4),
                            resultSet.getTime(5),
                            resultSet.getDate(6),
                            resultSet.getInt(7)
                    );
                    onsiteCourseList.add(course);
                }
            }
        }
        catch(Exception error){
            error.printStackTrace();
        }
        return onsiteCourseList;
    }

    public ObservableList<OnlineCourse> getOnlineCourseList(){
        ObservableList<OnlineCourse> onlineCourseList = FXCollections.observableArrayList();

        String query = "Select * from OnlineCourse";
        try{
            ResultSet resultSet = connect.excuteQuery(query);
            if(resultSet != null){
                while(resultSet.next()){
                    OnlineCourse course = new OnlineCourse(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getString(3)
                    );
                    onlineCourseList.add(course);
                }
            }
        }
        catch(Exception error){
            error.printStackTrace();
        }
        return onlineCourseList;
    }

    public ObservableList<Course> getAllCourseList(){
        ObservableList<Course> CourseList = FXCollections.observableArrayList();

        String query = "Select * from Course";
        try{
            ResultSet resultSet = connect.excuteQuery(query);
            if(resultSet != null){
                while(resultSet.next()){
                    Course course = new Course(
                            resultSet.getInt("CourseID"),
                            resultSet.getString("CourseName"),
                            resultSet.getString("CourseDescription"),
                            resultSet.getString("CourseImage"),
                            resultSet.getDate("DateCreate"),
                            resultSet.getDate("DateStart"),
                            resultSet.getDate("DateEnd")
                    );
                    CourseList.add(course);
                }
            }
        }
        catch(Exception error){
            error.printStackTrace();
        }
        return CourseList;
    }

    public Assignment setAssignment(Assignment assignment){
        int result = 0;
        String query = "insert into Assignment values('" + assignment.getCourseId() + "', '" + assignment.getPersonId() + "')";
        try{
            result = connect.ExecuteUpdate(query);
        }
        catch(Exception error){
            error.printStackTrace();
        }
        if(result == 0){
            return null;
        }
        return assignment;
    }
}
