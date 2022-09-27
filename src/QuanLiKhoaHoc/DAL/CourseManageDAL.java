package QuanLiKhoaHoc.DAL;

import QuanLiKhoaHoc.DTO.Course;
import QuanLiKhoaHoc.DTO.OnlineCourse;
import QuanLiKhoaHoc.DTO.OnsiteCourse;
import QuanLiKhoaHoc.GUI.CourseManage.Controller;
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
//Them khoa hoc moi
    public Course addCourse(Course course) throws Exception {
        String query = "Insert into Course value (";
        query = query + "'"+ course.getCourseId()+"'";
        query = query +"," +"'"+ course.getCourseName()+"'";
        query = query + "," + "'" + course.getCourseDescription()+"'";
        query = query +"," +"'"+ course.getDateStart()+"'";
        query = query +"," +"'"+ course.getDateEnd()+"'";
        query = query +"," +"'"+ course.getDateCreate()+"'";
        query = query +"";
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

    public Course deleteCourse(Course course) throws Exception {
        String query = "Delete from Course Where CourseId='" + course.getCourseId() +"'";
        connect.getConnect();
        connect.ExecuteUpdate(query);
        System.out.println(query);
        connect.closeConnect();
        return course;
    }
    public OnlineCourse deleteOnlineCourse(OnlineCourse onlineCourse) throws Exception {
        String query = "Delete from OnlineCourse Where OnlineCourseId= "+ onlineCourse.getOnlineCourseId() ;
        connect.getConnect();
        connect.ExecuteUpdate(query);
        connect.closeConnect();
        return onlineCourse;
    }
    public Controller.OnlineTableView deleteOnlineCourseTest(Controller.OnlineTableView onlineTableView){
        String query = "Delete from OnlineCourse Where OnlineCourseId= "+ onlineTableView.getOnlineCourseIdOnlineTableColumn()
                + " and CourseId= "+onlineTableView.getCourseIdOnlineTableColumn();

        try {
            connect.ExecuteUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return onlineTableView;
    }

    public Controller.OnsiteTableView deleteOnsiteCourseTest(Controller.OnsiteTableView onsiteTableView){
        String query = "Delete from OnsiteCourse Where OnsiteCourseId= "+ onsiteTableView.getOnsiteCourseIdOnsiteTableColumn()
                + " and CourseId= "+onsiteTableView.getCourseIdOnsiteTableColumn();

        try {
            connect.ExecuteUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return onsiteTableView;
    }
}
