package QuanLiKhoaHoc.DTO;

public class ResultDetail {
    int DetailId, ResultId;
    float Score;
    String Rating;

    public ResultDetail(int detailId, int resultId, float score, String rating) {
        DetailId = detailId;
        ResultId = resultId;
        Score = score;
        Rating = rating;
    }

    public int getDetailId() {
        return DetailId;
    }

    public void setDetailId(int detailId) {
        DetailId = detailId;
    }

    public int getResultId() {
        return ResultId;
    }

    public void setResultId(int resultId) {
        ResultId = resultId;
    }

    public float getScore() {
        return Score;
    }

    public void setScore(float score) {
        Score = score;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    @Override
    public String toString() {
        return "ResultDetail{" +
                "DetailId=" + DetailId +
                ", ResultId=" + ResultId +
                ", Score=" + Score +
                ", Rating='" + Rating + '\'' +
                '}';
    }
}
