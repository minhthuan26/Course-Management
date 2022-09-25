package QuanLiKhoaHoc.DAL.TeacherManage;

import QuanLiKhoaHoc.DAL.ConnectDB;
import QuanLiKhoaHoc.DTO.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
                            resultSet.getString(5),resultSet.getDate(6),
                            resultSet.getString(7));
                    teacherList.add(teacher);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return teacherList;
    }
}
