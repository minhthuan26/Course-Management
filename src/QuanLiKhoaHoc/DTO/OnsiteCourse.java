package QuanLiKhoaHoc.DTO;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class OnsiteCourse extends Course{
    int OnsiteCoureId, CourseId, RoomId, LessonQuantity;
    Time TimeStart, TimeEnd;
    LocalDate DayOccur;


    public LocalDate getDayOccur() {
        return DayOccur;
    }

    public void setDayOccur(LocalDate dayOccur) {
        DayOccur = dayOccur;
    }

    public OnsiteCourse(int courseId, String courseName, String courseDescription, LocalDate dateCreate , LocalDate dateStart, LocalDate dateEnd, String courseImage , int onsiteCoureId, int roomId, Time timeStart, Time timeEnd, LocalDate dayOccur, int lessonQuantity) {
        super(courseId, courseName, courseDescription, dateCreate, dateStart, dateEnd, courseImage);

        OnsiteCoureId = onsiteCoureId;
        CourseId = courseId;
        RoomId = roomId;
        LessonQuantity = lessonQuantity;
        TimeStart = timeStart;
        TimeEnd = timeEnd;
        DayOccur = dayOccur;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int courseId) {
        CourseId = courseId;
    }

    public int getOnsiteCoureId() {
        return OnsiteCoureId;
    }

    public void setOnsiteCoureId(int onsiteCoureId) {
        OnsiteCoureId = onsiteCoureId;
    }

    public int getRoomId() {
        return RoomId;
    }

    public void setRoomId(int roomId) {
        RoomId = roomId;
    }

    public int getLessonQuantity() {
        return LessonQuantity;
    }

    public void setLessonQuantity(int lessonQuantity) {
        LessonQuantity = lessonQuantity;
    }

    public Time getTimeStart() {
        return TimeStart;
    }

    public void setTimeStart(Time timeStart) {
        TimeStart = timeStart;
    }

    public Time getTimeEnd() {
        return TimeEnd;
    }

    public void setTimeEnd(Time timeEnd) {
        TimeEnd = timeEnd;
    }



    @Override
    public String toString() {
        return "OnsiteCourse{" +
                "OnsiteCoureId=" + OnsiteCoureId +
                ", RoomId=" + RoomId +
                ", LessonQuantity=" + LessonQuantity +
                ", TimeStart=" + TimeStart +
                ", TimeEnd=" + TimeEnd +
                ", DayOccur=" + DayOccur +
                '}';
    }
}
