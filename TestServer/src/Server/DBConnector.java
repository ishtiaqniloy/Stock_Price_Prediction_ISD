package Server;

import java.sql.*;

public class DBConnector {
    private static Connection connection = null;

    public static Connection getConnection() {
        if(connection==null){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection=DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/spp_db","root","3985");

                System.out.println("Connection Closed:"+connection.isClosed());
            }catch(Exception e){
                e.printStackTrace();
            }
        }


        return connection;
    }


}


