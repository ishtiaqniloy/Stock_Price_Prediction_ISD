package Server;

import Model.CompanyEnlistment;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class DatabaseUpdater {
    public static void main(String []args){

        Connection connection = DBConnector.getConnection();

        try{
            Scanner scanner = new Scanner(new FileInputStream(new File("Input")));
            String []strings;
            Statement stmt = null;
            stmt = connection.createStatement();
            Random random = new Random();


            int i = 0;

            String query = "select * from spp_db.`company enlistment` where Enlist_Id = ?;";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, "ADM");


            ResultSet rs = preparedStmt.executeQuery() ;

            while(rs.next()){
                System.out.println(rs.getString(1)+ " " + rs.getString(2)+ " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getDate(6));

            }

            ///insert code
//            while(scanner.hasNextLine()){
//                strings = scanner.nextLine().split("\t");
//                String query = "INSERT INTO `User Share` " + "VALUES (?, ?, ?);";
//
//                PreparedStatement preparedStmt = connection.prepareStatement(query);
//                preparedStmt.setString(1, strings[0]);
//                preparedStmt.setString(2,strings[1]);
//                preparedStmt.setString(3,Float.toString(random.nextFloat()*1000));
//
//                preparedStmt.executeUpdate();
//
//                System.out.println(i++);
//            }

            ///select code
//            String query2 = "select * from spp_db.`company enlistment`;" ;
//            ResultSet rs = stmt.executeQuery(query2) ;
//            int j = 0;
//            while(rs.next()){
//                System.out.println((j++) + " " + rs.getString(1)+ " " + rs.getString(2)+ " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getDate(6));
//                CompanyEnlistment companyEnlistment = new CompanyEnlistment(rs.getString(1), rs.getString(2),rs.getString(3) , (int)Float.parseFloat(rs.getString(5)), rs.getDate(6), (int)Float.parseFloat(rs.getString(4)));
//
//                System.out.println(companyEnlistment);
//            }

            System.out.println("Done executing");

            ///delete code
//            String query3 = "delete from spp_db.`company` where `Trade_Code` = 'Trade Code';" ;
//            stmt.executeUpdate(query3);

            System.out.println("Done executing all queries");

        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
