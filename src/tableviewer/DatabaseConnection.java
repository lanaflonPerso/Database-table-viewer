package tableviewer;

import java.sql.*;

/**
 * Created by Tomov on 2.8.2017 г..
 */
public class DatabaseConnection {
    private static  String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static  String DB_URL; //= "jdbc:mysql://localhost:3306/information_schema"
    private static  String USER; // = "Dimitar"
    private static  String PASS; //  = "123456"
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private static DatabaseConnection connReference;
    private DatabaseConnection(String user, String pass, String databaseAddress) {
        DB_URL = "jdbc:mysql://"+databaseAddress+"/";
        USER = user;
        PASS = pass;
        registerJDBC();
        openConn();
        prepareStatement();
    }
    public static DatabaseConnection getInstance(String user, String pass, String databaseAddress) {
        if (connReference == null) {
            connReference = new DatabaseConnection(user,pass,databaseAddress);
        }
        return connReference;
    }
    private void registerJDBC() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
    private void openConn() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void prepareStatement() {
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet queryTry(String sql) throws SQLException{
        rs = stmt.executeQuery(sql);
        return rs;
    }
    public void closeConnection(){
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException se2) {
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
