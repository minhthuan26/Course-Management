package QuanLiKhoaHoc.DAL.AssignmentManage;

import QuanLiKhoaHoc.DAL.ConnectDB;
import QuanLiKhoaHoc.DTO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AssignmentDAL {

    ConnectDB connect = new ConnectDB();

    public ObservableList<Person> getTeacherList() {
        ObservableList<Person> teacherList = FXCollections.observableArrayList();
        String query = "Select * " +
                "from Person, PersonRole " +
                "where Person.PersonId=PersonRole.PersonId " +
                "and PersonRole.RoleId=1";
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
                            resultSet.getDate(6),
                            resultSet.getString(7)
                    );
                    teacherList.add(teacher);
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return teacherList;
    }

    public ObservableList<OnsiteCourse> getOnsiteCourseList() {
        ObservableList<OnsiteCourse> onsiteCourseList = FXCollections.observableArrayList();
        ObservableList<Course> courseList = getAllCourseList();
        for (Course course : courseList) {
            String query = "Select * from OnsiteCourse where CourseId='" + course.getCourseId() + "'";
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
                                resultSet.getDate(6),
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
            String query = "Select * from OnlineCourse where CourseId='" + course.getCourseId() + "'";
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
                            resultSet.getDate("DateEnd"),
                            resultSet.getDate("DateCreate"),
                            resultSet.getDate("DateStart"),
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
        } catch (Exception error) {
            error.printStackTrace();
        }
        if (result == 0) {
            return null;
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
}
