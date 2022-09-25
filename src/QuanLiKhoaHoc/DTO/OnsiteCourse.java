package QuanLiKhoaHoc.DTO;

import java.sql.Date;
import java.sql.Time;

public class OnsiteCourse extends Course{
    int OnsiteCoureId, RoomId, LessonQuantity;
    Time TimeStart, TimeEnd;
    Date DayOccur;

    public OnsiteCourse(int courseId, String courseName, String courseDescription,Date dateCreate , Date dateStart, Date dateEnd,String courseImage , int onsiteCoureId, int roomId, int lessonQuantity, Time timeStart, Time timeEnd, Date dayOccur) {
        super(courseId, courseName, courseDescription, dateCreate, dateStart, dateEnd, courseImage);
        OnsiteCoureId = onsiteCoureId;
        RoomId = roomId;
        LessonQuantity = lessonQuantity;
        TimeStart = timeStart;
        TimeEnd = timeEnd;
        DayOccur = dayOccur;
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

    public Date getDayOccur() {
        return DayOccur;
    }

    public void setDayOccur(Date dayOccur) {
        DayOccur = dayOccur;
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
