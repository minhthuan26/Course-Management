package QuanLiKhoaHoc.BUS.StudentManage;

import QuanLiKhoaHoc.DAL.StudentManage.StudentManageDAL;
import QuanLiKhoaHoc.DTO.CourseRegister;
import QuanLiKhoaHoc.DTO.Person;
import QuanLiKhoaHoc.DTO.PersonRole;
import javafx.collections.ObservableList;

public class StudentBUS {

    public StudentManageDAL studentManageDAL = new StudentManageDAL();
    public ObservableList<Person>studentList;

    public ObservableList<Person> getStudentList(){
        return studentList= studentManageDAL.getStudentList();
    }
    // tao studentrole dua tren student
    public PersonRole addStudentRole(int id){
        return studentManageDAL.addStudentRole(id);
    }
    // goi ham tao student tu DAL
    public Person addStudent(String FirstName, String LastName, String Email, String PhoneNumber, String date){
        return studentManageDAL.addStudent(FirstName,LastName,Email,PhoneNumber,date);
    }
    public PersonRole deletePersonRole(int id){
        return studentManageDAL.deletePersonRole(id);
    }
    public Person deletePerson(Person person){
        return studentManageDAL.deletePerson(person);
    }
    public CourseRegister getPersonFromCourseRegisterByID(int id){return studentManageDAL.getPersonFromCourseRegisterByID(id);}
    public Person updateStudent(Person student){
        return studentManageDAL.updateStudent(student);
    }
    public ObservableList<Person> getStudentSearch(int id){return studentManageDAL.getStudentSearch(id);}
}
