package QuanLiKhoaHoc.BUS.ResultManage;

import QuanLiKhoaHoc.DAL.ResultManage.ResultDAL;
import QuanLiKhoaHoc.DTO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.Map;

public class ResultBUS {
    public ResultDAL resultDAL = new ResultDAL();
    private ObservableList<Course> allCourseList;
    private ObservableList<OnsiteCourse> onsiteCourseList;
    private ObservableList<OnlineCourse> onlineCourseList;
    private ObservableList<Person> studentNoneResultList;
    private ObservableList<Person> allStudentList;

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

    public ObservableList<String> getStudentNoneResultListToGUI(int courseId){
        studentNoneResultList = getStudentNoneResultList(courseId);
        ObservableMap<Integer, String> studentNoneResulNameAndIdtList = FXCollections.observableHashMap();
        for(Person person : studentNoneResultList){
            studentNoneResulNameAndIdtList.put(person.getPersonId(), String.join(" ", person.getFirstName(), person.getLastName()));
        }
        ObservableList<String> studentNameListToGUI = FXCollections.observableArrayList();
        for(Map.Entry<Integer, String> teacher : studentNoneResulNameAndIdtList.entrySet()){
            studentNameListToGUI.add(teacher.getKey() + "_" + teacher.getValue());
        }
        return studentNameListToGUI;
    }

    public ObservableMap<Integer, String> getAllStudentListToGUI(){
        allStudentList = getAllStudentList();
        ObservableMap<Integer, String> allStudentNameAndIdList = FXCollections.observableHashMap();
        for(Person person : allStudentList)
            allStudentNameAndIdList.put(person.getPersonId(), String.join(" ", person.getFirstName(), person.getLastName()));
        return allStudentNameAndIdList;
    }

    public ObservableMap<Integer, String> getAllCourseListToGUI(){
        allCourseList = getAllCourseList();
        ObservableMap<Integer, String> allCourseNameAndIdList = FXCollections.observableHashMap();
        for(Course course : allCourseList)
            allCourseNameAndIdList.put(course.getCourseId(), course.getCourseName());
        return allCourseNameAndIdList;
    }
}
