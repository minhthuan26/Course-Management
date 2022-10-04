package QuanLiKhoaHoc.DAL.ResultManage;

import QuanLiKhoaHoc.DAL.ConnectDB;
import QuanLiKhoaHoc.DTO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class ResultDAL {
    ConnectDB connect = new ConnectDB();

    public ObservableList<Person> getAllStudentList() {
        ObservableList<Person> allStudentList = FXCollections.observableArrayList();
        String query = "Select * " +
                "from Person, PersonRole " +
                "where Person.PersonId=PersonRole.PersonId " +
                "and PersonRole.RoleId=2";
        try {
            ResultSet resultSet = connect.excuteQuery(query);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Person student = new Person(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getDate(6).toLocalDate(),
                            resultSet.getString(7)
                    );
                    allStudentList.add(student);
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return allStudentList;
    }

    public ObservableList<Person> getStudentNoneResultList(int courseId) {
        ObservableList<Person> studentNoneResulttList = FXCollections.observableArrayList();
        String query = "Select * " +
                "from Person, PersonRole " +
                "where Person.PersonId=PersonRole.PersonId " +
                "and PersonRole.RoleId=2 " +
                "and not exists (select * from CourseRegister " +
                                "where Person.PersonId=CourseRegister.PersonId " +
                                "and CourseRegister.CourseId="+ courseId + ")";
        try {
            ResultSet resultSet = connect.excuteQuery(query);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Person student = new Person(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getDate(6).toLocalDate(),
                            resultSet.getString(7)
                    );
                    studentNoneResulttList.add(student);
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return studentNoneResulttList;
    }

    public ObservableList<OnsiteCourse> getOnsiteCourseList() {
        ObservableList<OnsiteCourse> onsiteCourseList = FXCollections.observableArrayList();
        ObservableList<Course> courseList = getAllCourseList();
        for (Course course : courseList) {
            String query = "Select * from OnsiteCourse " +
                    "where CourseId='" + course.getCourseId() + "' ";
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
                    "where CourseId='" + course.getCourseId() + "' ";
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

    public CourseRegister getCourseRegisterByIds(int courseId, int personId) {
        String query = "select * from CourseRegister " +
                "where CourseId=" + courseId + " and " + "PersonId=" + personId;
        try {
            ResultSet resultSet = connect.excuteQuery(query);
            if (resultSet != null) {
                while (resultSet.next()) {
                    return new CourseRegister(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getInt(3)
                    );
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return null;
    }

    public ObservableList<CourseRegister> getAllCourseRegisterList() {
        ObservableList<CourseRegister> allCourseRegisterList = FXCollections.observableArrayList();
        String query = "select * from CourseRegister";
        try {
            ResultSet resultSet = connect.excuteQuery(query);
            if (resultSet != null) {
                while (resultSet.next()) {
                    CourseRegister courseRegister = new CourseRegister(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getInt(3)
                    );
                    allCourseRegisterList.add(courseRegister);
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return allCourseRegisterList;
    }

    public CourseRegister setCourseRegister(int courseId, int personId) {
        int result = 0;
        String query = "insert into CourseRegister values('" + courseId + "', '" + personId + "')";
        try {
            result = connect.ExecuteUpdate(query);
            if (result == 0)
                return null;
        } catch (Exception error) {
            error.printStackTrace();
        }
        return getCourseRegisterByIds(courseId, personId);
    }

    public CourseResult getCourseResultByRegisterId(int registerId) {
        String query = "select * from CourseResult " +
                "where RegisterId=" + registerId;
        try {
            ResultSet resultSet = connect.excuteQuery(query);
            if (resultSet != null) {
                while (resultSet.next()) {
                    return new CourseResult(
                            resultSet.getInt(1),
                            resultSet.getInt(2)
                    );
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return null;
    }

    public CourseResult setCourseResult(int courseRegister) {
        int result = 0;
        String query = "insert into CourseResult values(" + courseRegister + ")";
        try {
            result = connect.ExecuteUpdate(query);
            if (result == 0)
                return null;
        } catch (Exception error) {
            error.printStackTrace();
        }
        return getCourseResultByRegisterId(courseRegister);
    }

    public ResultDetail getResultDetailByResultId(int resultId){
        String query = "select * from ResultDetail " +
                "where ResultId=" + resultId;
        try{
            ResultSet resultSet = connect.excuteQuery(query);
            if (resultSet != null) {
                while (resultSet.next()) {
                    return new ResultDetail(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getFloat(3),
                            resultSet.getString(4)
                    );
                }
            }
        }
        catch (Exception error) {
            error.printStackTrace();
        }
        return null;
    }

    public ResultDetail setResultDetail(int resultId, float score, String rating) {
        int result = 0;
        String query = "insert into ResultDetail values(" + resultId + ", " + score + ", N'" + rating + "')";
        try{
            result = connect.ExecuteUpdate(query);
            if (result == 0)
                return null;
        }
        catch(Exception error){
            error.printStackTrace();
        }
        return getResultDetailByResultId(resultId);
    }

    public CourseRegister deleteCourseRegister(CourseRegister courseRegister){
        int result = 0;
        String query = "delete CourseRegister " +
                "where RegisterId=" + courseRegister.getRegisterId();
        try {
            result = connect.ExecuteUpdate(query);
            if(result == 0)
                return null;
        }
        catch (Exception error) {
            error.printStackTrace();
        }
        return courseRegister;
    }

    public CourseResult deleteCourseResult(CourseResult courseResult){
        int result = 0;
        String query = "delete CourseResult " +
                "where ResultId=" + courseResult.getResultId();
        try {
            result = connect.ExecuteUpdate(query);
            if(result == 0)
                return null;
        }
        catch (Exception error) {
            error.printStackTrace();
        }
        return courseResult;
    }

    public ResultDetail deleteResultDetail(ResultDetail resultDetail){
        int result = 0;
        String query = "delete ResultDetail " +
                "where DetailId=" + resultDetail.getDetailId();
        try {
            result = connect.ExecuteUpdate(query);
            if(result == 0)
                return null;
        }
        catch (Exception error) {
            error.printStackTrace();
        }
        return resultDetail;
    }

    public Course getCourseById(int courseId){
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

    public Person getStudentById(int studentId){
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
