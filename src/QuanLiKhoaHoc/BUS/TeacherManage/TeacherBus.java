package QuanLiKhoaHoc.BUS.TeacherManage;

import QuanLiKhoaHoc.DAL.StudentManage.StudentManageDAL;
import QuanLiKhoaHoc.DAL.TeacherManage.TeacherManageDAL;
import QuanLiKhoaHoc.DTO.Person;
import javafx.collections.ObservableList;

public class TeacherBus {
    public TeacherManageDAL teacherManageDAL = new TeacherManageDAL();
    public ObservableList<Person> teacherList;

    public ObservableList<Person> getTeacherList(){
        return teacherList= teacherManageDAL.getTeacherList();
    }
}
