package QuanLiKhoaHoc.DAL;

import QuanLiKhoaHoc.DTO.Course;
import QuanLiKhoaHoc.DTO.OnlineCourse;
import QuanLiKhoaHoc.DTO.OnsiteCourse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseManageDAL {
    ConnectDB connect = new ConnectDB();
// Lay tat ca du lieu tu bang Course
    public ObservableList<Course> getCourseList(){
        ObservableList<Course> courseList = FXCollections.observableArrayList();
        String query = "Select * from Course";
        try{
            ResultSet resultSet = connect.excuteQuery(query);
            if(resultSet != null){
                while(resultSet.next()){
                    Course course = new Course(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDate(4),
                            resultSet.getDate(5),
                            resultSet.getDate(6),
                            resultSet.getString(7)
                            );
                    courseList.add(course);
                }
            }
        }
        catch(Exception error){
            error.printStackTrace();
        }
        return courseList;
    }
    // Lay tat ca du lieu tu bang OnlineCourse

    public ObservableList<OnlineCourse> getOnlineCourseList() {
        ObservableList<OnlineCourse> onlineCourseList = FXCollections.observableArrayList();
        ObservableList<Course> courseList = getCourseList();
        for (Course course : courseList) {
            String query = "Select * from OnlineCourse where CourseId =" + course.getCourseId();
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
                                resultSet.getString(3)
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
    public ObservableList<OnsiteCourse> getOnsiteCourseList(){
        ObservableList<OnsiteCourse> onsiteCourseList = FXCollections.observableArrayList();

        String queryOnsiteCourse = "Select * from OnsiteCourse";
        String queryCourse = "Select * from Course";

        try{
            ResultSet resultSetOnsiteCourse = connect.excuteQuery(queryOnsiteCourse);
            ResultSet resultSetCourse = connect.excuteQuery(queryCourse);
            if(resultSetOnsiteCourse != null){
                while(resultSetOnsiteCourse.next()){
                    OnsiteCourse onsiteCourse = new OnsiteCourse(
                            resultSetCourse.getInt(1),
                            resultSetCourse.getString(2),
                            resultSetCourse.getString(3),
                            resultSetCourse.getDate(4),
                            resultSetCourse.getDate(5),
                            resultSetCourse.getDate(6),
                            resultSetCourse.getString(7),
                            resultSetOnsiteCourse.getInt(8),
                            resultSetOnsiteCourse.getInt(9),
                            resultSetOnsiteCourse.getTime(10),
                            resultSetOnsiteCourse.getTime(11),
                            resultSetOnsiteCourse.getDate(12),
                            resultSetOnsiteCourse.getInt(13)
                    );
                    onsiteCourseList.add(onsiteCourse);
                }
            }
        }
        catch(Exception error){
            error.printStackTrace();
        }
        return onsiteCourseList;
    }

//Them khoa hoc moi
    public Course addCourse(Course course) throws Exception {
        String query = "Insert into Course value (";
        query = query + "'"+ course.getCourseId()+"'";
        query = query +"," +"'"+ course.getCourseName()+"'";
        query = query + "," + "'" + course.getCourseDescription()+"'";
        query = query +"," +"'"+ course.getDateStart()+"'";
        query = query +"," +"'"+ course.getDateEnd()+"'";
        query = query +"," +"'"+ course.getDateCreate()+"'";
        query = query +"," +"'"+ course.getCourseImage()+"'";
        query = query +")";
        connect.getConnect();
        connect.ExecuteUpdate(query);
        System.out.println(query);
        connect.closeConnect();
        return course;
    }

    public  void addOnlineCourse(Course onlineCourse) throws Exception {
         onlineCourse = addCourse(onlineCourse);
         String query = "Insert into OnlineCourse value (";
    }
//Sua Khoa Hoc

    public void editCourse(Course course) throws Exception {
        String query = "Update Course Set ";
        query= query + "CourseName="+"'"+ course.getCourseName()+"'";
        query= query + ",CourseDescription="+"'"+ course.getCourseDescription()+"'";
        query= query + ",DateCreate="+"'"+ course.getDateCreate()+"'";
        query= query + ",DateStart="+"'"+ course.getDateStart()+"'";
        query= query + ",DateEnd="+"'"+ course.getDateEnd()+"'";
        query= query + ",CourseImage="+"'"+ course.getCourseImage()+"'";
        query= query + " "+"Where CourseId='"+ course.getCourseId()+"'";
        connect.getConnect();
        connect.ExecuteUpdate(query);
        System.out.println(query);
        connect.closeConnect();
    }

    public void deleteCourse(String id) throws Exception {
        String query = "Delete from Course Where CourseId='" + id +"'";
        connect.getConnect();
        connect.ExecuteUpdate(query);
        System.out.println(query);
        connect.closeConnect();
    }
}
