package QuanLiKhoaHoc.DAL.TeacherManage;

import QuanLiKhoaHoc.DAL.ConnectDB;
import QuanLiKhoaHoc.DTO.Assignment;
import QuanLiKhoaHoc.DTO.Person;
import QuanLiKhoaHoc.DTO.PersonRole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;

public class TeacherManageDAL {
    ConnectDB connect = new ConnectDB();
    public ObservableList<Person> getTeacherList(){
        ObservableList<Person> teacherList = FXCollections.observableArrayList();
        String query = "Select *"+
                "from Person, PersonRole " +
                "where Person.PersonId=PersonRole.PersonId " +
                "and PersonRole.RoleId=1";
        try {
            ResultSet resultSet =connect.excuteQuery(query);
            if (resultSet!=null){
                while(resultSet.next()){
                    Person teacher = new Person(resultSet.getInt(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getString(4),
                            resultSet.getString(5),resultSet.getDate(6).toLocalDate(),
                            resultSet.getString(7));
                    teacherList.add(teacher);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return teacherList;
    }
    // lay teacher dua tren ID
    public ObservableList<Person> getTeacherSearch(int id){
        ObservableList<Person> teacherSearch = FXCollections.observableArrayList();
        String query = "Select * from Person, PersonRole where PersonRole.PersonId="+id +"and PersonRole.RoleId=1 and Person.PersonId=PersonRole.PersonId";
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
                    teacherSearch.add(teacher);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return teacherSearch;
    }

    // handle duplicate teacher by email
    public Person getTeacherByEmail(String email){
        String query ="Select * from Person where Person.Email = '" +email+"'";
        try {
            ResultSet rs = connect.excuteQuery(query);
            if(rs!=null){
                while (rs.next()){
                    return   new Person(
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    // add teacher after check duplicate email
    public Person addTeacher (String FirstName, String LastName, String Email, String PhoneNumber, String Date){
        Person person= getTeacherByEmail(Email);
        // neu person nay k trung email thi tao person moi
        if(person==null){
            int result=0;
            String query ="INSERT INTO Person Values (N'" + FirstName
                    +  "', N'" + LastName +  "', N'" + Email
                    +  "', '" + PhoneNumber
                    +  "', '" + Date+"','')";
            try {
                result = connect.ExecuteUpdate(query);
                if (result == 0) {
                    return null;
                }
            } catch (Exception error) {
                error.printStackTrace();
            }

            return person=getTeacherByEmail(Email);
        }
        return null;
    }
    // get PersonRole after insert new Person
    public PersonRole getPersonRoleByIDs(int id){
        String query = "select * from PersonRole where PersonRole.PersonId = "+id + " and PersonRole.RoleId=1" ;
        try{
            ResultSet rs = connect.excuteQuery(query);
            if(rs!=null){
                while (rs.next()){
                    return new PersonRole(
                            rs.getInt(1),
                            rs.getInt(2));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    // add new PersonRole for teacher
    public PersonRole addTeacherRole(int id){
        String query = "insert into PersonRole values(1" +"," +id+")";
        int result = 0;
        try {
            result=connect.ExecuteUpdate(query);
            if (result==0) {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // lay ra personrole
        PersonRole personRole =getPersonRoleByIDs(id);
        return personRole;
    }
    // Xoa person role cho teacher
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

    // xoa giao vien trong person

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
    public Assignment getIdAssignment(int id){
        String query = "Select * from Assignment Where PersonID = " + id;

        try {
            ResultSet resultSet = connect.excuteQuery(query);
            while (resultSet.next()){
                return new Assignment(
                        resultSet.getInt(1),
                        resultSet.getInt(2)
                );
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public Person getTeacherById(int id){
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

    public Person updateTeacher(Person teacher){
        String query = "update Person " +
                "set FirstName=N'" + teacher.getFirstName() + "', LastName=N'" + teacher.getLastName() + "', " +
                "PhoneNumber='" + teacher.getPhoneNumber() + "', DateOfBirth='" + teacher.getDateOfBirth() +
                "' where PersonId=" + teacher.getPersonId();
        int result = 0;
        try{
            result = connect.ExecuteUpdate(query);
            if(result == 0)
                return null;
        }
        catch (Exception error){
            error.printStackTrace();
        }
        return getTeacherById(teacher.getPersonId());
    }
}
