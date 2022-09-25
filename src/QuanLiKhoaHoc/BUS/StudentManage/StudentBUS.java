package QuanLiKhoaHoc.BUS.StudentManage;

import QuanLiKhoaHoc.DAL.StudentManage.StudentManageDAL;
import QuanLiKhoaHoc.DTO.Person;
import javafx.collections.ObservableList;

public class StudentBUS {
    public StudentManageDAL studentManageDAL = new StudentManageDAL();
    public ObservableList<Person>studentList;

    public ObservableList<Person> getStudentList(){
        return studentList= studentManageDAL.getStudentList();
    }
}
