package QuanLiKhoaHoc.BUS.CourseManage;

import QuanLiKhoaHoc.DAL.CourseManage.CourseManageDAL;
import QuanLiKhoaHoc.DTO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.time.LocalDate;
import java.util.Map;

public class CourseManageBUS {



    CourseManageDAL courseManageDAL = new CourseManageDAL();
    public static ObservableList<Course> allCourseList;
    public static ObservableList<OnlineCourse> allOnlineCourseList;
    public static ObservableList<OnsiteCourse> allOnsiteCourseList;

    public ObservableList<Course> getAllCourseList() {
        return allCourseList = courseManageDAL.getCourseList();
    }

    public ObservableList<OnlineCourse> getAllOnlineCourseList() {
        return allOnlineCourseList= courseManageDAL.getOnlineCourseList();
    }

    public ObservableList<OnsiteCourse> getAllOnsiteCourseList() {
        return allOnsiteCourseList = courseManageDAL.getOnsiteCourseList();
    }

    public Course deleteOnlineCourseTest(int courseId){
        return courseManageDAL.deleteOnlineCourseTest(courseId);
    }
    public Course deleteOnsiteCourseTest(int courseId){
        return courseManageDAL.deleteOnsiteCourseTest(courseId);
    }

    public Course deleteCourseOnsite(Course courseId){
        return courseManageDAL.deleteCourseOnsite(courseId);
    }
    public Course deleteCourseOnline(Course course){
        return courseManageDAL.deleteCourseOnline(course);
    }
    public Assignment getIdAssignment(int id){
        return courseManageDAL.getIdAssignment(id);
    }
    public CourseRegister getCourseFromCourseRegisterByID(int id){
        return courseManageDAL.getCourseFromCourseRegisterByID(id);
    }
    public Course addCourse(String name, String desc, LocalDate cre, LocalDate start, LocalDate end){
        return courseManageDAL.addCourse(name , desc, cre, start, end);
    }
    public Course addOnlineCourse(int id, String url){
        return courseManageDAL.addOnlineCourse(id, url);
    }
    public  Course addOnsiteCourse(int id, int less, LocalDate occur){return  courseManageDAL.addOnsiteCourse(id, less, occur);}
    public Course editCourse(String name, String desc, LocalDate datecre, LocalDate start, LocalDate end, int id){
        return courseManageDAL.editCourse(name, desc, datecre, start, end,id);
    }
    public Course editOnline(String url, int id){
        return courseManageDAL.editOnline(url,id);
    }
    public Course editOnsite(int lesson, LocalDate occur, int id){
        return courseManageDAL.editOnsite(lesson,occur,id);
    }
    //Tao class moi merge 2 bang chung lai (Online + course)
    public static class OnlineTableView {
        public int getCourseIdOnlineTableColumn() {
            return courseIdOnlineTableColumn;
        }

        public void setCourseIdOnlineTableColumn(int courseIdOnlineTableColumn) {
            this.courseIdOnlineTableColumn = courseIdOnlineTableColumn;
        }

        public int getOnlineCourseIdOnlineTableColumn() {
            return onlineCourseIdOnlineTableColumn;
        }

        public void setOnlineCourseIdOnlineTableColumn(int onlineCourseIdOnlineTableColumn) {
            this.onlineCourseIdOnlineTableColumn = onlineCourseIdOnlineTableColumn;
        }

        public String getCourseNameOnlineTableColumn() {
            return courseNameOnlineTableColumn;
        }

        public void setCourseNameOnlineTableColumn(String courseNameOnlineTableColumn) {
            this.courseNameOnlineTableColumn = courseNameOnlineTableColumn;
        }

        public String getCourseDescriptionOnlineTableColumn() {
            return courseDescriptionOnlineTableColumn;
        }

        public void setCourseDescriptionOnlineTableColumn(String courseDescriptionOnlineTableColumn) {
            this.courseDescriptionOnlineTableColumn = courseDescriptionOnlineTableColumn;
        }

        public String getCourseImageOnlineTableColumn() {
            return courseImageOnlineTableColumn;
        }

        public void setCourseImageOnlineTableColumn(String courseImageOnlineTableColumn) {
            this.courseImageOnlineTableColumn = courseImageOnlineTableColumn;
        }

        public String getCourseUrlOnlineTableColumn() {
            return courseUrlOnlineTableColumn;
        }

        public void setCourseUrlOnlineTableColumn(String courseUrlOnlineTableColumn) {
            this.courseUrlOnlineTableColumn = courseUrlOnlineTableColumn;
        }

        public LocalDate getDateCreateOnlineTableView() {
            return dateCreateOnlineTableView;
        }

        public void setDateCreateOnlineTableView(LocalDate dateCreateOnlineTableView) {
            this.dateCreateOnlineTableView = dateCreateOnlineTableView;
        }

        public LocalDate getDateStartOnlineTableView() {
            return dateStartOnlineTableView;
        }

        public void setDateStartOnlineTableView(LocalDate dateStartOnlineTableView) {
            this.dateStartOnlineTableView = dateStartOnlineTableView;
        }

        public LocalDate getDateEndOnlineTableView() {
            return dateEndOnlineTableView;
        }

        public void setDateEndOnlineTableView(LocalDate dateEndOnlineTableView) {
            this.dateEndOnlineTableView = dateEndOnlineTableView;
        }

        public OnlineTableView() {
        }

        public OnlineTableView(int courseIdOnlineTableColumn, int onlineCourseIdOnlineTableColumn) {
            this.courseIdOnlineTableColumn = courseIdOnlineTableColumn;
            this.onlineCourseIdOnlineTableColumn = onlineCourseIdOnlineTableColumn;
        }

        int courseIdOnlineTableColumn, onlineCourseIdOnlineTableColumn;

        public OnlineTableView(int courseIdOnlineTableColumn) {
            this.courseIdOnlineTableColumn = courseIdOnlineTableColumn;
        }

        String courseNameOnlineTableColumn, courseDescriptionOnlineTableColumn, courseImageOnlineTableColumn, courseUrlOnlineTableColumn;
        LocalDate dateCreateOnlineTableView, dateStartOnlineTableView, dateEndOnlineTableView;
    }

    //Lay du lieu tu class merge cua Onsite
    public ObservableList<OnsiteTableView> getOnsiteTableView() {
        getAllCourseList();
        ObservableList<OnsiteTableView> onsiteTableViews = FXCollections.observableArrayList();
        getAllCourseList();
        ObservableList<OnsiteCourse> onsiteTableViewList = getAllOnsiteCourseList();
        for (OnsiteCourse onsiteCourse : onsiteTableViewList) {
            CourseManageBUS.OnsiteTableView onsiteTableView = new CourseManageBUS.OnsiteTableView();
            onsiteTableView.onsiteCourseIdOnsiteTableColumn = onsiteCourse.getOnsiteCoureId();
            onsiteTableView.courseIdOnsiteTableColumn = onsiteCourse.getCourseId();
            onsiteTableView.lessonQuantityOnsiteTableColumn = onsiteCourse.getLessonQuantity();
            onsiteTableView.dayOccurOnsiteTableColumn = onsiteCourse.getDayOccur();
            for (Course course : allCourseList) {
                if (onsiteCourse.getCourseId() == course.getCourseId()) {
                    onsiteTableView.courseNameOnsiteTableColumn = course.getCourseName();
                    onsiteTableView.courseDescriptionOnsiteTableColumn = course.getCourseDescription();
                    onsiteTableView.dateCreateOnsiteTableColumn = course.getDateCreate();
                    onsiteTableView.dateStartOnsiteTableColumn = course.getDateStart();
                    onsiteTableView.dateEndOnsiteTableColumn = course.getDateEnd();
                }
            }
            onsiteTableViews.add(onsiteTableView);

        }
        return onsiteTableViews;
    }

    //Lay du lieu tu class merge cua Online
    public ObservableList<OnlineTableView> getOnlineTableView() {
        getAllCourseList();
        ObservableList<OnlineTableView> onlineTableViews = FXCollections.observableArrayList();
        getAllCourseList();
        ObservableList<OnlineCourse> onlineTableViewList = getAllOnlineCourseList();
        for (OnlineCourse onlineCourse : onlineTableViewList) {
            OnlineTableView onlineTableView = new OnlineTableView();
            onlineTableView.courseIdOnlineTableColumn = onlineCourse.getCourseId();
            onlineTableView.onlineCourseIdOnlineTableColumn = onlineCourse.getOnlineCourseId();
            onlineTableView.courseUrlOnlineTableColumn = onlineCourse.getCourseUrl();
            for (Course course : allCourseList) {
                if (course.getCourseId() == onlineCourse.getCourseId()) {
                    onlineTableView.dateCreateOnlineTableView = course.getDateCreate();
                    onlineTableView.dateStartOnlineTableView = course.getDateStart();
                    onlineTableView.dateEndOnlineTableView = course.getDateEnd();
                    onlineTableView.courseNameOnlineTableColumn = course.getCourseName();
                    onlineTableView.courseDescriptionOnlineTableColumn = course.getCourseDescription();
                    onlineTableView.courseImageOnlineTableColumn = course.getCourseImage();
                }
            }
            onlineTableViews.add(onlineTableView);

        }
        return onlineTableViews;
    }

    //Tao class moi merge 2 bang chung lai (Onsite + course)
    public static class OnsiteTableView {

        public int getCourseIdOnsiteTableColumn() {
            return courseIdOnsiteTableColumn;
        }

        public void setCourseIdOnsiteTableColumn(int courseIdOnsiteTableColumn) {
            this.courseIdOnsiteTableColumn = courseIdOnsiteTableColumn;
        }

        public int getOnsiteCourseIdOnsiteTableColumn() {
            return onsiteCourseIdOnsiteTableColumn;
        }

        public void setOnsiteCourseIdOnsiteTableColumn(int onsiteCourseIdOnsiteTableColumn) {
            this.onsiteCourseIdOnsiteTableColumn = onsiteCourseIdOnsiteTableColumn;
        }

        public int getLessonQuantityOnsiteTableColumn() {
            return lessonQuantityOnsiteTableColumn;
        }

        public void setLessonQuantityOnsiteTableColumn(int lessonQuantityOnsiteTableColumn) {
            this.lessonQuantityOnsiteTableColumn = lessonQuantityOnsiteTableColumn;
        }

        public String getCourseDescriptionOnsiteTableColumn() {
            return courseDescriptionOnsiteTableColumn;
        }

        public void setCourseDescriptionOnsiteTableColumn(String courseDescriptionOnsiteTableColumn) {
            this.courseDescriptionOnsiteTableColumn = courseDescriptionOnsiteTableColumn;
        }

        public String getCourseNameOnsiteTableColumn() {
            return courseNameOnsiteTableColumn;
        }

        public void setCourseNameOnsiteTableColumn(String courseNameOnsiteTableColumn) {
            this.courseNameOnsiteTableColumn = courseNameOnsiteTableColumn;
        }


        int courseIdOnsiteTableColumn;

        public OnsiteTableView(int courseIdOnsiteTableColumn, int onsiteCourseIdOnsiteTableColumn) {
            this.courseIdOnsiteTableColumn = courseIdOnsiteTableColumn;
            this.onsiteCourseIdOnsiteTableColumn = onsiteCourseIdOnsiteTableColumn;
        }

        public OnsiteTableView() {
        }

        public OnsiteTableView(int courseIdOnsiteTableColumn) {
            this.courseIdOnsiteTableColumn = courseIdOnsiteTableColumn;
        }

        int onsiteCourseIdOnsiteTableColumn;
        int lessonQuantityOnsiteTableColumn;
        String courseDescriptionOnsiteTableColumn, courseNameOnsiteTableColumn;

        public LocalDate getDateStartOnsiteTableColumn() {
            return dateStartOnsiteTableColumn;
        }

        public void setDateStartOnsiteTableColumn(LocalDate dateStartOnsiteTableColumn) {
            this.dateStartOnsiteTableColumn = dateStartOnsiteTableColumn;
        }

        public LocalDate getDateEndOnsiteTableColumn() {
            return dateEndOnsiteTableColumn;
        }

        public void setDateEndOnsiteTableColumn(LocalDate dateEndOnsiteTableColumn) {
            this.dateEndOnsiteTableColumn = dateEndOnsiteTableColumn;
        }

        public LocalDate getDayOccurOnsiteTableColumn() {
            return dayOccurOnsiteTableColumn;
        }

        public void setDayOccurOnsiteTableColumn(LocalDate dayOccurOnsiteTableColumn) {
            this.dayOccurOnsiteTableColumn = dayOccurOnsiteTableColumn;
        }

        public LocalDate getDateCreateOnsiteTableColumn() {
            return dateCreateOnsiteTableColumn;
        }

        public void setDateCreateOnsiteTableColumn(LocalDate dateCreateOnsiteTableColumn) {
            this.dateCreateOnsiteTableColumn = dateCreateOnsiteTableColumn;
        }

        LocalDate dateStartOnsiteTableColumn, dateEndOnsiteTableColumn, dayOccurOnsiteTableColumn, dateCreateOnsiteTableColumn;
    }




    public ObservableList<String> getOnsiteList() {
        getAllCourseList();
        getAllOnsiteCourseList();
        ObservableMap<Integer, String> onsiteCourseNameAndIdList = FXCollections.observableHashMap();
        for (OnsiteCourse onsiteCourse : allOnsiteCourseList) {
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
        getAllOnlineCourseList();
        ObservableMap<Integer, String> onlineCourseNameAndIdList = FXCollections.observableHashMap();
        for (OnlineCourse onlineCourse : allOnlineCourseList) {
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


//    public ObservableMap<Integer, String> getAllCourseNameAndIdList() {
//        getAllCourseList();
//        ObservableMap<Integer, String> allCourseNameAndIdList = FXCollections.observableHashMap();
//        for (Course course : allCourseList)
//            allCourseNameAndIdList.put(course.getCourseId(), course.getCourseName());
//        return allCourseNameAndIdList;
//    }
//
//
//
//    public ObservableList<String> getAllCourseListToGUI() {
//        ObservableMap<Integer, String> allCourseNameAndIdList = getAllCourseNameAndIdList();
//        ObservableList<String> allCourseNameListToGUI = FXCollections.observableArrayList();
//        for (Map.Entry<Integer, String> teacher : allCourseNameAndIdList.entrySet()) {
//            allCourseNameListToGUI.add(teacher.getKey() + "_" + teacher.getValue());
//        }
//        return allCourseNameListToGUI;
//    }
        public ObservableList<OnsiteCourse> getOnsiteCourseListbyId(int id){
                return courseManageDAL.getOnsiteCourseListbyId(id);
        }
    public ObservableList<OnlineCourse> getOnlineCourseListbyId(int id){
        return courseManageDAL.getOnlineCourseListbyId(id);
    }
        public Course getCourseListbyId(int id){
            return courseManageDAL.getCourseById(id);
        }

    public ObservableList<OnsiteTableView> getOnsiteTableViewbyId(int id) {
        getCourseListbyId(id);
        ObservableList<OnsiteTableView> onsiteTableViews = FXCollections.observableArrayList();
        getCourseListbyId(id);
        ObservableList<OnsiteCourse> onsiteTableViewList = getOnsiteCourseListbyId(id);
        for (OnsiteCourse onsiteCourse : onsiteTableViewList) {
            if (onsiteCourse.getCourseId()==id){
                OnsiteTableView onsiteTableView = new CourseManageBUS.OnsiteTableView();
                onsiteTableView.onsiteCourseIdOnsiteTableColumn = onsiteCourse.getOnsiteCoureId();
                onsiteTableView.courseIdOnsiteTableColumn = onsiteCourse.getCourseId();
                onsiteTableView.lessonQuantityOnsiteTableColumn = onsiteCourse.getLessonQuantity();
                onsiteTableView.dayOccurOnsiteTableColumn = onsiteCourse.getDayOccur();
                for (Course course : allCourseList) {
                    if (onsiteCourse.getCourseId() == course.getCourseId()) {
                        onsiteTableView.courseNameOnsiteTableColumn = course.getCourseName();
                        onsiteTableView.courseDescriptionOnsiteTableColumn = course.getCourseDescription();
                        onsiteTableView.dateCreateOnsiteTableColumn = course.getDateCreate();
                        onsiteTableView.dateStartOnsiteTableColumn = course.getDateStart();
                        onsiteTableView.dateEndOnsiteTableColumn = course.getDateEnd();
                    }
                }
                onsiteTableViews.add(onsiteTableView);
            }
        }
        return onsiteTableViews;
    }

    public ObservableList<OnlineTableView> getOnlineTableViewbyId(int id) {
        getCourseListbyId(id);
        ObservableList<OnlineTableView> onlineTableViews = FXCollections.observableArrayList();
        getCourseListbyId(id);
        ObservableList<OnlineCourse> onlineTableViewList = getOnlineCourseListbyId(id);
        for (OnlineCourse onlineCourse : onlineTableViewList) {
            if (onlineCourse.getCourseId()==id){
                OnlineTableView onlineTableView = new OnlineTableView();
                onlineTableView.courseIdOnlineTableColumn = onlineCourse.getCourseId();
                onlineTableView.onlineCourseIdOnlineTableColumn = onlineCourse.getOnlineCourseId();
                onlineTableView.courseUrlOnlineTableColumn = onlineCourse.getCourseUrl();
                for (Course course : allCourseList) {
                    if (onlineCourse.getCourseId() == course.getCourseId()) {
                        onlineTableView.courseNameOnlineTableColumn = course.getCourseName();
                        onlineTableView.courseDescriptionOnlineTableColumn = course.getCourseDescription();
                        onlineTableView.dateCreateOnlineTableView = course.getDateCreate();
                        onlineTableView.dateStartOnlineTableView = course.getDateStart();
                        onlineTableView.dateEndOnlineTableView = course.getDateEnd();
                    }
                }
                onlineTableViews.add(onlineTableView);
            }
        }
        return onlineTableViews;
    }


}
