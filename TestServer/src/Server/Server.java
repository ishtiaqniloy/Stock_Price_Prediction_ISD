package Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Server {


    public static void main(String []args){
        Connection connection = DBConnector.getConnection();

        try{
            Scanner scanner = new Scanner(new FileInputStream(new File("Input")));
            String []strings;
            Statement stmt = null;
            stmt = connection.createStatement();
            Random random = new Random();

            int i = 0;


            System.out.println("Done executing all queries");

        }catch (Exception e){
            e.printStackTrace();
        }



//        try {
//            stmt = connection.createStatement();
//            String query = "INSERT INTO Company (Trade_Code, `Company Name`, `Authorized Capital`, `Paidup Capital`, `Outstanding Share Number`, `Sector`)" + "VALUES (?, ?, ?, ?, ?, ?);";
//
//            String query2 = "select * from spp_db.company;" ;
//            PreparedStatement preparedStmt = connection.prepareStatement(query);
//            preparedStmt.setString(1, "");
//            preparedStmt.setString(2,"nanata Steel");
//            preparedStmt.setString(3,"60000000");
//            preparedStmt.setString(4,"267242053");
//            preparedStmt.setString(5,"60000");
//            preparedStmt.setString(6,"steel");
//            preparedStmt.executeUpdate();
//
//            ResultSet rs = stmt.executeQuery(query2) ;
//            while(rs.next()){
//                System.out.println(rs.getString(1));
//            }
//            System.out.println("Done executing");
//            connection.close();
//            //System.out.println(rs.getString(1));
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }



    }





}
