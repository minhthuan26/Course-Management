package QuanLiKhoaHoc.DAL;

import java.sql.*;

public class ConnectDB {
    private final String hostName = "localhost";
    private final String dbName = "QuanLiKhoaHoc";
    private final String userName = "sa";
    private final String password = "26042001";
    private Connection connect;
    Statement st = null;
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
            connect = (Connection) DriverManager.getConnection(connectionURL);
            System.out.println("Connect Success!!!");
        } catch (SQLException error)
        {
            error.printStackTrace();
        }
        return connect;
    }

    public Statement getStatement() throws Exception{
        try{
            if(this.st == null || this.st.isClosed()){
                this.st=this.getConnect().createStatement();
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return this.st;
    }

    public ResultSet excuteQuery(String qry) throws Exception{
        try{
            this.rs = this.getStatement().executeQuery(qry);
        } catch (Exception ex){
            throw new Exception("Error: "+ex.getMessage()+"-"+qry);
        }
        return this.rs;
    }

    public int ExecuteUpdate(String qry) throws Exception{
        int res =0;
        try{
            res = this.getStatement().executeUpdate(qry);
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
