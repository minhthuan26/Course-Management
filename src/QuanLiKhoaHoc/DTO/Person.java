package QuanLiKhoaHoc.DTO;

import java.sql.Date;

public class Person {
    int PersonId;
    String FirstName, LastName, Email, PhoneNumber, PersonImage;
    Date DateOfBirth;

    public Person(int personId, String firstName, String lastName, String email, String phoneNumber, Date dateOfBirth, String personImage) {
        PersonId = personId;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        PhoneNumber = phoneNumber;
        PersonImage = personImage;
        DateOfBirth = dateOfBirth;
    }

    public int getPersonId() {
        return PersonId;
    }

    public void setPersonId(int personId) {
        PersonId = personId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPersonImage() {
        return PersonImage;
    }

    public void setPersonImage(String personImage) {
        PersonImage = personImage;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "PersonId=" + PersonId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", PersonImage='" + PersonImage + '\'' +
                ", DateOfBirth=" + DateOfBirth +
                '}';
    }
}
