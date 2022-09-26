package QuanLiKhoaHoc.BUS.ResultManage;

import QuanLiKhoaHoc.DAL.ResultManage.ResultDAL;
import QuanLiKhoaHoc.DTO.*;
import javafx.collections.ObservableList;

public class ResultBUS {
    public ResultDAL resultDAL = new ResultDAL();
    public static ObservableList<Course> allCourseList;
    public static ObservableList<OnsiteCourse> onsiteCourseList;
    public static ObservableList<OnlineCourse> onlineCourseList;
    public static ObservableList<Person> studentNoneResultList;
    public static ObservableList<Person> allStudentList;
    public static ObservableList<CourseRegister> allCourseRegisterList;

    public ObservableList<Course> getAllCourseList(){
        return allCourseList = resultDAL.getAllCourseList();
    }

    public ObservableList<Person> getStudentNoneResultList(int courseId){
        return studentNoneResultList = resultDAL.getStudentNoneResultList(courseId);
    }

    public CourseRegister getCourseRegisterByIds(int courseId, int personId){
        return resultDAL.getCourseRegisterByIds(courseId, personId);
    }

    public  CourseResult getCourseResultByRegisterId(int resultId){
        return resultDAL.getCourseResultByRegisterId(resultId);
    }

    public ResultDetail getResultDetailByResultId(int detailId){
        return resultDAL.getResultDetailByResultId(detailId);
    }

    public ObservableList<Person> getAllStudentList(){
        return allStudentList = resultDAL.getAllStudentList();
    }

    public ObservableList<OnlineCourse> getOnlineCourseList(){
        return onlineCourseList = resultDAL.getOnlineCourseList();
    }

    public ObservableList<OnsiteCourse> getOnsiteCourseList(){
        return onsiteCourseList = resultDAL.getOnsiteCourseList();
    }

    public CourseRegister setCourseRegister(int courseId, int personId){
        return resultDAL.setCourseRegister(courseId, personId);
    }

    public ObservableList<CourseRegister> getAllCourseRegisterList(){
        return resultDAL.getAllCourseRegisterList();
    }

    public CourseResult setCourseResult(int courseRegister){
        return resultDAL.setCourseResult(courseRegister);
    }

    public ResultDetail setResultDetail(int resultId, String score){
        float parseScore = 0;
        try{
            parseScore = Float.parseFloat(score);

            if(parseScore < 0)
                parseScore = 0;

            if(parseScore > 10)
                parseScore = 10;

            String rating = "";
            if(parseScore < 5){
                rating = "Kém";
            }
            else if(parseScore == 5){
                rating = "Trung bình";
            }
            else if(parseScore >= 8){
                rating = "Giỏi";
            }
            else{
                rating = "Khá";
            }
            return resultDAL.setResultDetail(resultId, parseScore, rating);
        }
        catch(Exception error){
            error.printStackTrace();
        }
        return null;
    }

    public ResultDetail deleteResultDetail(ResultDetail resultDetail){
        return resultDAL.deleteResultDetail(resultDetail);
    }

    public CourseResult deleteCourseResult(CourseResult courseResult){
        return resultDAL.deleteCourseResult(courseResult);
    }

    public CourseRegister deleteCourseRegister(CourseRegister courseRegister){
        return resultDAL.deleteCourseRegister(courseRegister);
    }
}
