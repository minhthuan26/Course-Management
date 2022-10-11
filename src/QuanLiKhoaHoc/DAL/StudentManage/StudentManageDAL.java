package QuanLiKhoaHoc.DAL.StudentManage;

import QuanLiKhoaHoc.DAL.ConnectDB;
import QuanLiKhoaHoc.DTO.CourseRegister;
import QuanLiKhoaHoc.DTO.Person;
import QuanLiKhoaHoc.DTO.PersonRole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class StudentManageDAL {
    ConnectDB connect = new ConnectDB();

    public ObservableList<Person> getStudentList() {
        ObservableList<Person> studentList = FXCollections.observableArrayList();
        String query = "Select *" +
                "from Person, PersonRole " +
                "where Person.PersonId=PersonRole.PersonId " +
                "and PersonRole.RoleId=2";
        try {
            ResultSet resultSet = connect.excuteQuery(query);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Person student = new Person(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getString(4),
                            resultSet.getString(5), resultSet.getDate(6).toLocalDate(),
                            resultSet.getString(7));
                    studentList.add(student);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }
    // lay student dua tren ID
    public ObservableList<Person> getStudentSearch(int id){
        ObservableList<Person> studentSearch = FXCollections.observableArrayList();
        String query = "Select * from Person, PersonRole where PersonRole.PersonId="+id +"and PersonRole.RoleId=2 and Person.PersonId=PersonRole.PersonId";
        try {
            ResultSet resultSet =connect.excuteQuery(query);
            if (resultSet!=null){
                while(resultSet.next()){
                    Person teacher = new Person(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getDate(6).toLocalDate(),
                            resultSet.getString(7));
                    studentSearch.add(teacher);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return studentSearch;
    }


    // xu ly neu user trung email se khong tao user moi
    public Person getStudentByEmail(String email) {
        String query = "Select * from Person where Person.Email = '" + email + "'";
        try {
            ResultSet rs = connect.excuteQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    return new Person(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getDate(6).toLocalDate(),
                            rs.getString(7)
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // lay person role khi da tao Person moi thoa dieu kien
    public PersonRole getPersonRoleByIDs(int id) {
        String query = "select * from PersonRole where PersonRole.PersonId = " + id + " and PersonRole.RoleId=2";
        try {
            ResultSet rs = connect.excuteQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    return new PersonRole(
                            rs.getInt(1),
                            rs.getInt(2));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // tao person role
    public PersonRole addStudentRole(int id) {
        String query = "insert into PersonRole values(2" + "," + id + ")";
        int result = 0;
        try {
            result = connect.ExecuteUpdate(query);
            if (result == 0) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // lay ra personrole
        PersonRole personRole = getPersonRoleByIDs(id);
        return personRole;
    }

    public Person addStudent(String FirstName, String LastName, String Email, String PhoneNumber, String dayNow) {
        Person person = getStudentByEmail(Email);
        // neu person nay k trung email thi tao person moi
        if (person == null) {
            int result = 0;
            String query = "INSERT INTO Person Values (N'" + FirstName
                    + "', N'" + LastName + "', N'" + Email
                    + "', '" + PhoneNumber
                    + "', '" + dayNow + "','')";
            try {
                result = connect.ExecuteUpdate(query);
                if (result == 0) {
                    return null;
                }
            } catch (Exception error) {
                error.printStackTrace();
            }

            return person = getStudentByEmail(Email);
        }
        return null;
    }

    // xoa person role trc khi xoa person
    public PersonRole deletePersonRole(int id){
        int result = 0;
        String query = "delete from PersonRole " +
                "where PersonId=" + id;
        try {
            result = connect.ExecuteUpdate(query);
            if (result == 0)
                return null;
        } catch (Exception error) {
            error.printStackTrace();
        }
        PersonRole personRole = getPersonRoleByIDs(id);
        return personRole;
    }
    // xoa person
    public Person deletePerson(Person person) {
        int result = 0;
        String query = "delete from Person " +
                "where PersonId=" +person.getPersonId();
        try {
            result = connect.ExecuteUpdate(query);
            if (result == 0)
                return null;
        } catch (Exception error) {
            error.printStackTrace();
        }
        return person;
    }
    public CourseRegister getPersonFromCourseRegisterByID(int id){
        String query = "Select * from CourseRegister Where PersonID = " + id;

        try {
            ResultSet rs = connect.excuteQuery(query);
            while (rs.next()){
                return new CourseRegister(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3)
                );
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public Person getStudentById(int id){
        String query = "select * from Person where PersonId=" + id;
        try{
            ResultSet resultSet = connect.excuteQuery(query);
            while (resultSet.next()){
                return new Person(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6).toLocalDate(),
                        resultSet.getString(7)
                );
            }
        }
        catch(Exception error){
            error.printStackTrace();
        }
        return null;
    }

    public Person updateStudent(Person student){
        String query = "update Person " +
                "set FirstName=N'" + student.getFirstName() + "', LastName=N'" + student.getLastName() + "', " +
                "PhoneNumber='" + student.getPhoneNumber() + "', DateOfBirth='" + student.getDateOfBirth() +
                "' where PersonId=" + student.getPersonId();
        int result = 0;
        try{
            result = connect.ExecuteUpdate(query);
            if(result == 0)
                return null;
        }
        catch (Exception error){
            error.printStackTrace();
        }
        return getStudentById(student.getPersonId());
    }

}
