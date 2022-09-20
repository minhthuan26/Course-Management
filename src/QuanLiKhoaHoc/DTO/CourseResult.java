package QuanLiKhoaHoc.DTO;

public class CourseResult {
    int ResultId, RegisterId;

    public CourseResult(int resultId, int registerId) {
        ResultId = resultId;
        RegisterId = registerId;
    }

    public int getResultId() {
        return ResultId;
    }

    public void setResultId(int resultId) {
        ResultId = resultId;
    }

    public int getRegisterId() {
        return RegisterId;
    }

    public void setRegisterId(int registerId) {
        RegisterId = registerId;
    }

    @Override
    public String toString() {
        return "CourseResult{" +
                "ResultId=" + ResultId +
                ", RegisterId=" + RegisterId +
                '}';
    }
}
