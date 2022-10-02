package QuanLiKhoaHoc.DAL;

import java.sql.*;

public class ConnectDB {
    private final String hostName = "localhost";
    private final String dbName = "QuanLiKhoaHoc";
    private final String userName = "sa";
    private final String password = "26042001";
    private Connection connect;
    PreparedStatement st;
    ResultSet rs = null;

    private String connectionURL = "jdbc:sqlserver://"
            + hostName + ":1433;databaseName=" + dbName + ";user=" + userName
            + ";password=" + password + "; encrypt=true; trustServerCertificate=true";

    public ConnectDB()
    {
        getConnect();
    }
    // sử dụng lớp
    public Connection getConnect()
    {
        connect = null;
        try {
            connect = DriverManager.getConnection(connectionURL);
//            System.out.println("Connect Success!!!");
        } catch (SQLException error)
        {
            error.printStackTrace();
        }
        return connect;
    }

    public ResultSet excuteQuery(String qry) throws Exception{
        try{
            this.st = connect.prepareStatement(qry);
            this.rs = this.st.executeQuery();
        } catch (Exception ex){
            throw new Exception("Error: "+ex.getMessage()+"-"+qry);
        }
        return this.rs;
    }

    public int ExecuteUpdate(String qry) throws Exception{
        int res =0;
        try{
            this.st = connect.prepareStatement(qry);
            res = this.st.executeUpdate();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return res;
    }

    public void closeConnect() throws SQLException{
        if(this.rs!=null && !this.rs.isClosed()){
            this.rs.close();
            this.rs=null;
        }
        if(this.st!=null && !this.st.isClosed()){
            this.st.close();
            this.st=null;
        }
        if(this.connect!=null && !this.connect.isClosed()){
            this.connect.close();
            this.connect=null;
        }
    }
}
