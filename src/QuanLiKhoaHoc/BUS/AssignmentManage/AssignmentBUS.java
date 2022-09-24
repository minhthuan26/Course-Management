package QuanLiKhoaHoc.BUS.AssignmentManage;

import QuanLiKhoaHoc.DAL.AssignmentManage.AssignmentDAL;

public class AssignmentBUS {
    public void Test(){
        AssignmentDAL assignment = new AssignmentDAL();
        System.out.println(assignment.getTeacherList().toString());
    }
}
