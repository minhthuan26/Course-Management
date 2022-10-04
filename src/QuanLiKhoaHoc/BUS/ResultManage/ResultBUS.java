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

    public ObservableList<Course> getAllCourseList() {
        return allCourseList = resultDAL.getAllCourseList();
    }

    public ObservableList<Person> getStudentNoneResultList(int courseId) {
        return studentNoneResultList = resultDAL.getStudentNoneResultList(courseId);
    }

    public CourseRegister getCourseRegisterByIds(int courseId, int personId) {
        return resultDAL.getCourseRegisterByIds(courseId, personId);
    }

    public CourseResult getCourseResultByRegisterId(int resultId) {
        return resultDAL.getCourseResultByRegisterId(resultId);
    }

    public ResultDetail getResultDetailByResultId(int detailId) {
        return resultDAL.getResultDetailByResultId(detailId);
    }

    public ObservableList<Person> getAllStudentList() {
        return allStudentList = resultDAL.getAllStudentList();
    }

    public ObservableList<OnlineCourse> getOnlineCourseList() {
        return onlineCourseList = resultDAL.getOnlineCourseList();
    }

    public ObservableList<OnsiteCourse> getOnsiteCourseList() {
        return onsiteCourseList = resultDAL.getOnsiteCourseList();
    }

    public CourseRegister setCourseRegister(int courseId, int personId) {
        return resultDAL.setCourseRegister(courseId, personId);
    }

    public ObservableList<CourseRegister> getAllCourseRegisterList() {
        return resultDAL.getAllCourseRegisterList();
    }

    public CourseResult setCourseResult(int courseRegister) {
        return resultDAL.setCourseResult(courseRegister);
    }

    public ResultDetail setResultDetail(int resultId, String score) {
        float parseScore = 0;
        try {
            parseScore = Float.parseFloat(score);

            if (parseScore < 0)
                parseScore = 0;

            if (parseScore > 10)
                parseScore = 10;

            String rating = "";
            if (parseScore < 5) {
                rating = "Kém";
            } else if (parseScore == 5) {
                rating = "Trung bình";
            } else if (parseScore >= 8) {
                rating = "Giỏi";
            } else {
                rating = "Khá";
            }
            return resultDAL.setResultDetail(resultId, parseScore, rating);
        } catch (Exception error) {
            error.printStackTrace();
        }
        return null;
    }

    public ResultDetail deleteResultDetail(ResultDetail resultDetail) {
        return resultDAL.deleteResultDetail(resultDetail);
    }

    public CourseResult deleteCourseResult(CourseResult courseResult) {
        return resultDAL.deleteCourseResult(courseResult);
    }

    public CourseRegister deleteCourseRegister(CourseRegister courseRegister) {
        return resultDAL.deleteCourseRegister(courseRegister);
    }

    public ObservableList<String> getOnsiteList() {
        getAllCourseList();
        getOnsiteCourseList();
        ObservableMap<Integer, String> onsiteCourseNameAndIdList = FXCollections.observableHashMap();
        for (OnsiteCourse onsiteCourse : onsiteCourseList) {
            for (Course course : allCourseList)
                if (course.getCourseId() == onsiteCourse.getCourseId()) {
                    onsiteCourseNameAndIdList.put(course.getCourseId(), course.getCourseName());
                    break;
                }
        }
        ObservableList<String> onsiteCourseListToGUI = FXCollections.observableArrayList();
        for (Map.Entry<Integer, String> onsiteCourse : onsiteCourseNameAndIdList.entrySet()) {
            onsiteCourseListToGUI.add(onsiteCourse.getKey() + "_" + onsiteCourse.getValue());
        }
        return onsiteCourseListToGUI;
    }

    public ObservableList<String> getOnlineList() {
        getAllCourseList();
        getOnlineCourseList();
        ObservableMap<Integer, String> onlineCourseNameAndIdList = FXCollections.observableHashMap();
        for (OnlineCourse onlineCourse : onlineCourseList) {
            for (Course course : allCourseList)
                if (course.getCourseId() == onlineCourse.getCourseId()) {
                    onlineCourseNameAndIdList.put(course.getCourseId(), course.getCourseName());
                    break;
                }
        }
        ObservableList<String> onlineCourseListToGUI = FXCollections.observableArrayList();
        for (Map.Entry<Integer, String> onlineCourse : onlineCourseNameAndIdList.entrySet()) {
            onlineCourseListToGUI.add(onlineCourse.getKey() + "_" + onlineCourse.getValue());
        }
        return onlineCourseListToGUI;
    }

    public ObservableList<String> getStudentNoneResultListToGUI(int courseId) {
        getStudentNoneResultList(courseId);
        ObservableMap<Integer, String> studentNoneResulNameAndIdtList = FXCollections.observableHashMap();
        for (Person person : studentNoneResultList) {
            studentNoneResulNameAndIdtList.put(person.getPersonId(), String.join(" ", person.getLastName(), person.getFirstName()));
        }
        ObservableList<String> studentNameListToGUI = FXCollections.observableArrayList();
        for (Map.Entry<Integer, String> teacher : studentNoneResulNameAndIdtList.entrySet()) {
            studentNameListToGUI.add(teacher.getKey() + "_" + teacher.getValue());
        }
        return studentNameListToGUI;
    }

    public ObservableMap<Integer, String> getAllStudentNameAndIdList() {
        getAllStudentList();
        ObservableMap<Integer, String> allStudentNameAndIdList = FXCollections.observableHashMap();
        for (Person person : allStudentList)
            allStudentNameAndIdList.put(person.getPersonId(), String.join(" ", person.getLastName(), person.getFirstName()));
        return allStudentNameAndIdList;
    }

    public ObservableList<String> getAllStudentListToGUI() {
        ObservableMap<Integer, String> allStudentNameAndIdList = getAllStudentNameAndIdList();
        ObservableList<String> allStudentNameListToGUI = FXCollections.observableArrayList();
        for (Map.Entry<Integer, String> teacher : allStudentNameAndIdList.entrySet()) {
            allStudentNameListToGUI.add(teacher.getKey() + "_" + teacher.getValue());
        }
        return allStudentNameListToGUI;
    }

    public ObservableMap<Integer, String> getAllCourseNameAndIdList() {
        getAllCourseList();
        ObservableMap<Integer, String> allCourseNameAndIdList = FXCollections.observableHashMap();
        for (Course course : allCourseList)
            allCourseNameAndIdList.put(course.getCourseId(), course.getCourseName());
        return allCourseNameAndIdList;
    }

    public ObservableList<String> getAllCourseListToGUI() {
        ObservableMap<Integer, String> allCourseNameAndIdList = getAllCourseNameAndIdList();
        ObservableList<String> allCourseNameListToGUI = FXCollections.observableArrayList();
        for (Map.Entry<Integer, String> teacher : allCourseNameAndIdList.entrySet()) {
            allCourseNameListToGUI.add(teacher.getKey() + "_" + teacher.getValue());
        }
        return allCourseNameListToGUI;
    }

    public ObservableList<ResultTableView> getResultTableViewList() {
        ObservableList<CourseRegister> allCourseRegisterList = getAllCourseRegisterList();
        ObservableList<ResultTableView> resultTableViewList = FXCollections.observableArrayList();
        ObservableMap<Integer, String> allStudentList = getAllStudentNameAndIdList();
        ObservableMap<Integer, String> allCourseList = getAllCourseNameAndIdList();
        for (CourseRegister courseRegister : allCourseRegisterList) {
            ResultTableView resultTableView = new ResultTableView();
            for (Map.Entry<Integer, String> student : allStudentList.entrySet()) {
                if (student.getKey() == courseRegister.getPersonId()) {
                    resultTableView.setStudentId(student.getKey());
                    resultTableView.setStudentName(student.getValue());
                    break;
                }
            }
            for (Map.Entry<Integer, String> course : allCourseList.entrySet()) {
                if (course.getKey() == courseRegister.getCourseId()) {
                    resultTableView.setCourseId(course.getKey());
                    resultTableView.setCourseName(course.getValue());
                    break;
                }
            }
            CourseResult courseResult = getCourseResultByRegisterId(courseRegister.getRegisterId());
            ResultDetail resultDetail = getResultDetailByResultId(courseResult.getResultId());
            resultTableView.setScore(resultDetail.getScore());
            resultTableView.setRating(resultDetail.getRating());
            resultTableViewList.add(resultTableView);
        }
        return resultTableViewList;
    }

    public ObservableList<ResultTableView> getSearchResultByCourse(int searchId) {
        ObservableList<CourseRegister> allCourseRegisterList = getAllCourseRegisterList();
        ObservableList<ResultTableView> resultTableViewList = FXCollections.observableArrayList();
        ObservableMap<Integer, String> allStudentList = getAllStudentNameAndIdList();
        Course course = resultDAL.getCourseById(searchId);

        for (CourseRegister courseRegister : allCourseRegisterList) {
            if(courseRegister.getCourseId() == searchId){
                ResultTableView resultTableView = new ResultTableView();
                resultTableView.setCourseId(course.getCourseId());
                resultTableView.setCourseName(course.getCourseName());

                for (Map.Entry<Integer, String> student : allStudentList.entrySet()) {
                    if (student.getKey() == courseRegister.getPersonId()) {
                        resultTableView.setStudentId(student.getKey());
                        resultTableView.setStudentName(student.getValue());
                    }
                }

                CourseResult courseResult = getCourseResultByRegisterId(courseRegister.getRegisterId());
                ResultDetail resultDetail = getResultDetailByResultId(courseResult.getResultId());
                resultTableView.setScore(resultDetail.getScore());
                resultTableView.setRating(resultDetail.getRating());
                resultTableViewList.add(resultTableView);
            }
        }
        return resultTableViewList;
    }

    public ObservableList<ResultTableView> getSearchResultByStudent(int searchId) {
        ObservableList<CourseRegister> allCourseRegisterList = getAllCourseRegisterList();
        ObservableList<ResultTableView> resultTableViewList = FXCollections.observableArrayList();
        ObservableMap<Integer, String> allCourseList = getAllCourseNameAndIdList();
        Person student = resultDAL.getStudentById(searchId);

        for (CourseRegister courseRegister : allCourseRegisterList) {
            if(courseRegister.getPersonId() == searchId){
                ResultTableView resultTableView = new ResultTableView();
                resultTableView.setStudentId(student.getPersonId());
                resultTableView.setStudentName(student.getLastName() + " " + student.getFirstName());

                for (Map.Entry<Integer, String> course : allCourseList.entrySet()) {
                    if (course.getKey() == courseRegister.getCourseId()) {
                        resultTableView.setCourseId(course.getKey());
                        resultTableView.setCourseName(course.getValue());
                    }
                }

                CourseResult courseResult = getCourseResultByRegisterId(courseRegister.getRegisterId());
                ResultDetail resultDetail = getResultDetailByResultId(courseResult.getResultId());
                resultTableView.setScore(resultDetail.getScore());
                resultTableView.setRating(resultDetail.getRating());
                resultTableViewList.add(resultTableView);
            }
        }
        return resultTableViewList;
    }

    public static class ResultTableView {
        private int CourseId, StudentId;
        private String CourseName, StudentName, Rating;
        private float Score;

        public int getCourseId() {
            return CourseId;
        }

        public void setCourseId(int courseId) {
            CourseId = courseId;
        }

        public int getStudentId() {
            return StudentId;
        }

        public void setStudentId(int studentId) {
            StudentId = studentId;
        }

        public String getCourseName() {
            return CourseName;
        }

        public void setCourseName(String courseName) {
            CourseName = courseName;
        }

        public String getStudentName() {
            return StudentName;
        }

        public void setStudentName(String studentName) {
            StudentName = studentName;
        }

        public String getRating() {
            return Rating;
        }

        public void setRating(String rating) {
            Rating = rating;
        }

        public float getScore() {
            return Score;
        }

        public void setScore(float score) {
            Score = score;
        }

        @Override
        public String toString() {
            return "ResultTableView{" +
                    "CourseId=" + CourseId +
                    ", StudentId=" + StudentId +
                    ", CourseName='" + CourseName + '\'' +
                    ", StudentName='" + StudentName + '\'' +
                    ", Rating='" + Rating + '\'' +
                    ", Score=" + Score +
                    '}';
        }
    }
}
