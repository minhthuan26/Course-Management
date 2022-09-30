package QuanLiKhoaHoc.DAL;

import QuanLiKhoaHoc.DTO.Assignment;
import QuanLiKhoaHoc.DTO.Course;
import QuanLiKhoaHoc.DTO.OnlineCourse;
import QuanLiKhoaHoc.DTO.OnsiteCourse;
import QuanLiKhoaHoc.GUI.CourseManage.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;

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
                            resultSet.getDate(4).toLocalDate(),
                            resultSet.getDate(5).toLocalDate(),
                            resultSet.getDate(6).toLocalDate(),
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
        ObservableList<Course> courseList = getCourseList();
        for (Course course : courseList) {
            String query = "Select * from OnsiteCourse where CourseId ="+ course.getCourseId();
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
//Them khoa hoc moi
    public  Course addCourse(String name, String desc, LocalDate datecre, LocalDate start, LocalDate end) {
        Course course = getCourseByName(name);
        if (course==null) {
            String query = "Insert into Course values('" +
                    name + "', '" +
                    desc + "', '" +
                    datecre + "', '" +
                    start + "', '" +
                    end + "', " +
                    "''"+ ")";
            int result = 0;
            try {
                result = connect.ExecuteUpdate(query);
                if (result == 0) return null;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return getCourseByName(name);
        }
        return null;
    }

    public Course addOnlineCourse (int courseId, String url){
        String query = "Insert into OnlineCourse values("+
                courseId+",'" + url+"')";
        int result = 0;
        try{
            result = connect.ExecuteUpdate(query);
            if (result==0) return null;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return getCourseById(courseId);
    }

    public Course addOnsiteCourse (int courseId, int lessonQuan, LocalDate occur){
        Time start = new Time(1,1,1);
        Time end = new Time(6,6,6);
        String query = "Insert into OnsiteCourse values("+
                courseId+","+1+",'"+start  +"','"+end + "','" + occur+"','"+lessonQuan+"')";
        int result = 0;
        try{
            result = connect.ExecuteUpdate(query);
            if (result==0) return null;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return getCourseById(courseId);
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



    public Course getCourseByName(String name){
        String query = "select * from Course where CourseName='" + name+"'";
        try{
            ResultSet resultSet = connect.excuteQuery(query);
            while(resultSet.next()){
                return new Course(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4).toLocalDate(),
                        resultSet.getDate(5).toLocalDate(),
                        resultSet.getDate(6).toLocalDate(),
                        resultSet.getString(7)
                );
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Course getCourseById(int courseId){
        String query = "select * from Course where CourseId=" + courseId;
        try{
            ResultSet resultSet = connect.excuteQuery(query);
            while(resultSet.next()){
                return new Course(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4).toLocalDate(),
                        resultSet.getDate(5).toLocalDate(),
                        resultSet.getDate(6).toLocalDate(),
                        resultSet.getString(7)
                );
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Course deleteOnlineCourseTest(int courseId){
        String query = "Delete from OnlineCourse Where CourseId= "+courseId;
        int result = 0;
        try {
            result = connect.ExecuteUpdate(query);
            if (result==0) return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Course course = getCourseById(courseId);
        return course;
    }

    public Assignment getIdAssignment(int id){
        String query = "Select * from Assignment Where CourseId = " + id;

            try {
                ResultSet resultSet = connect.excuteQuery(query);
                while (resultSet.next()){
                    return new Assignment(
                            resultSet.getInt(1),
                            resultSet.getInt(2)
                    );
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        return null;
    }

    public Course deleteOnsiteCourseTest(int courseId){
        String query = "Delete from OnsiteCourse Where CourseId= "+courseId;
        int result = 0;
        try {
            result = connect.ExecuteUpdate(query);
            if (result==0) return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Course course = getCourseById(courseId);
        return course;
    }
    public Course deleteCourseOnline(Course course){
        String query = "Delete from Course Where CourseId="+course.getCourseId();
        int result=0;
        try {
            result = connect.ExecuteUpdate(query);
            if (result == 0){return null;}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return course;
    }
    public Course deleteCourseOnsite(Course course){
        String query = "Delete from Course Where CourseId="+course.getCourseId();
        int result=0;
        try {
            result = connect.ExecuteUpdate(query);
            if (result == 0){return null;}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return course;
    }
}
