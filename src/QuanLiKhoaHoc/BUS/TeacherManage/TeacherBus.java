package QuanLiKhoaHoc.BUS.TeacherManage;

import QuanLiKhoaHoc.DAL.TeacherManage.TeacherManageDAL;
import QuanLiKhoaHoc.DTO.Assignment;
import QuanLiKhoaHoc.DTO.Person;
import QuanLiKhoaHoc.DTO.PersonRole;
import javafx.collections.ObservableList;


public class TeacherBus {
    public TeacherManageDAL teacherManageDAL = new TeacherManageDAL();
    public ObservableList<Person> teacherList;

    public ObservableList<Person> getTeacherList(){
        return teacherList= teacherManageDAL.getTeacherList();
    }
    public PersonRole addTeacherRole(int id){
        return teacherManageDAL.addTeacherRole(id);
    }
    // goi ham tao student tu DAL
    public Person addTeacher(String FirstName, String LastName, String Email, String PhoneNumber, String date){
        return teacherManageDAL.addTeacher(FirstName,LastName,Email,PhoneNumber,date);
    }
    public PersonRole deletePersonRole(int id){
        return teacherManageDAL.deletePersonRole(id);
    }
    public Person deletePerson(Person person){
        return teacherManageDAL.deletePerson(person);
    }
    public Assignment getAssignmenByID(int id){return teacherManageDAL.getIdAssignment(id);}
    public Person updateTeacher(Person teacher){
        return teacherManageDAL.updateTeacher(teacher);
    }
    public ObservableList<Person> getTeacherSearch(int id){return teacherManageDAL.getTeacherSearch(id);}
}
