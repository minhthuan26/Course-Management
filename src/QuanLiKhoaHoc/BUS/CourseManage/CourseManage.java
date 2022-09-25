package QuanLiKhoaHoc.BUS.CourseManage;

import QuanLiKhoaHoc.DAL.CourseManageDAL;

public class CourseManage {
    public void Test(){
        CourseManageDAL courseManageDAL = new CourseManageDAL();
        System.out.println(courseManageDAL.getCourseList().toString());
    }
}
