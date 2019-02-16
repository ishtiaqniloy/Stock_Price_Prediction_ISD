package Server;

import Model.*;
import javafx.util.Pair;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

class ServerThread implements Runnable{
    Socket socket;
    InputStream inputStream;
    OutputStream outputStream;

    BufferedReader bufferedReader;
    PrintWriter printWriter;

    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    ServerThread(Socket socket){

        System.out.println("Connection accepted from " + socket.getInetAddress() +":" + socket.getPort());

        this.socket = socket;


        try
        {
            inputStream = this.socket.getInputStream();
            outputStream = this.socket.getOutputStream();

            objectOutputStream = new ObjectOutputStream(outputStream);
//            objectInputStream = new ObjectInputStream(inputStream);

            bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
            printWriter = new PrintWriter(this.outputStream);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void run(){
        String str;

        Connection connection = DBConnector.getConnection();
        Statement stmt;
        try {
            str = bufferedReader.readLine();
            System.out.println(str);

            if(str.equalsIgnoreCase("Send List")){
                stmt = connection.createStatement();
                String query2 = "select * from spp_db.`company enlistment`;" ;
                ResultSet rs = stmt.executeQuery(query2) ;
                int j = 0;



                ArrayList<CompanyEnlistment> companyEnlistments = new ArrayList<CompanyEnlistment>();

                while(rs.next()){
                    System.out.println((j++) + " " + rs.getString(1)+ " " + rs.getString(2)+ " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getDate(6));
                    CompanyEnlistment companyEnlistment = new CompanyEnlistment(rs.getString(1), rs.getString(2),rs.getString(3) , (int)Float.parseFloat(rs.getString(5)), rs.getDate(6), (int)Float.parseFloat(rs.getString(4)));

                    companyEnlistments.add(companyEnlistment);
                }

                System.out.println(companyEnlistments.size());
                objectOutputStream.writeObject(companyEnlistments.size());
                objectOutputStream.flush();

                for (CompanyEnlistment companyEnlistment: companyEnlistments) {
                    System.out.println("sending: " + companyEnlistment );

                    objectOutputStream.writeObject(companyEnlistment);
                    objectOutputStream.flush();
                }



                str = bufferedReader.readLine();

                System.out.println(str);

                socket.close();
            }
            else if(str.equalsIgnoreCase("Search")){

                String searchText = bufferedReader.readLine().trim();
                System.out.println("Received Entry Code = " + searchText);

                stmt = connection.createStatement();
                String query2 = "select * from spp_db.`company enlistment`;" ;
                ResultSet rs = stmt.executeQuery(query2) ;

                ArrayList<CompanyEnlistment> companyEnlistments = new ArrayList<CompanyEnlistment>();

                while(rs.next()){
                    if(rs.getString(2).contains(searchText)){
                        CompanyEnlistment companyEnlistment = new CompanyEnlistment(rs.getString(1), rs.getString(2),rs.getString(3) , (int)Float.parseFloat(rs.getString(5)), rs.getDate(6), (int)Float.parseFloat(rs.getString(4)));
                        companyEnlistments.add(companyEnlistment);
                    }

                }


                System.out.println(companyEnlistments.size());
                objectOutputStream.writeObject(companyEnlistments.size());
                objectOutputStream.flush();

                for (CompanyEnlistment companyEnlistment: companyEnlistments) {
                    System.out.println("sending: " + companyEnlistment );

                    objectOutputStream.writeObject(companyEnlistment);
                    objectOutputStream.flush();
                }



                str = bufferedReader.readLine();

                System.out.println(str);

                socket.close();
            }
            else if(str.equalsIgnoreCase("Send Entry")){
                String entryCode = bufferedReader.readLine().trim();
                System.out.println("Received Entry Code = " + entryCode);


                String query = "select * from spp_db.`company enlistment` where Enlist_Id = ?;";

                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, entryCode);


                ResultSet rs = preparedStmt.executeQuery() ;

                CompanyEnlistment companyEnlistment = null;
                CompanyEntry companyEntry = null;
                String name = null;
                while(rs.next()){
                    //System.out.println(rs.getString(1)+ " " + rs.getString(2)+ " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getDate(6));
                    companyEnlistment = new CompanyEnlistment(rs.getString(1), rs.getString(2),rs.getString(3) , (int)Float.parseFloat(rs.getString(5)), rs.getDate(6), (int)Float.parseFloat(rs.getString(4)));
                    System.out.println(companyEnlistment);
                }

                query = "select * from spp_db.`company` where Trade_Code = ?;";

                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, companyEnlistment.getTradeCode());


                rs = preparedStmt.executeQuery() ;

                rs.next();
                name = rs.getString(2);

                companyEntry = new CompanyEntry(companyEnlistment.getEnlistID(), companyEnlistment.getTradeCode(), companyEnlistment.getStCode(), name, companyEnlistment.getLastTradingPrice());

                query = "select * from spp_db.`share history` where Enlist_Id = ?;";

                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, companyEnlistment.getEnlistID());

                rs = preparedStmt.executeQuery() ;

                while (rs.next()){
                    ShareHistory shareHistory = new ShareHistory(companyEnlistment.getEnlistID(), (int)rs.getFloat(3), rs.getDate(2 ) );
                    companyEntry.historyList.add(shareHistory);
                    companyEntry.shareGraph.points.add(new Pair<>(shareHistory.getDate(), shareHistory.getClosingPrice()));
                }


                query = "select * from spp_db.`share prediction` where Enlist_Id = ?;";

                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, companyEnlistment.getEnlistID());

                rs = preparedStmt.executeQuery() ;

                while (rs.next()){
                    SharePrediction sharePrediction = new SharePrediction(companyEnlistment.getEnlistID(), rs.getDate(2 ), (int)rs.getFloat(3));
                    companyEntry.predictionList.add(sharePrediction);
                    companyEntry.shareGraph.points.add(new Pair<>(sharePrediction.getDate(), sharePrediction.getFutureClosingPrice()));
                }

                objectOutputStream.writeObject(companyEntry);
                objectOutputStream.flush();


                str = bufferedReader.readLine();

                System.out.println(str);

                socket.close();
            }
            else if(str.equalsIgnoreCase("Send Market")){
                String stCode = bufferedReader.readLine().trim();
                System.out.println("Received Stock Code = " + stCode);

                Graph graph = new Graph();

                String  query = "select * from spp_db.`market history` where St_Code = ?;";

                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, stCode);


                ResultSet rs = preparedStmt.executeQuery() ;

                while (rs.next()){
                    Pair <Date, Integer> point = new Pair <Date, Integer>(rs.getDate(2), (int)rs.getFloat(3));
                    graph.points.add(point);
                }


                query = "select * from spp_db.`market prediction` where St_Code = ?;";

                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, stCode);

                rs = preparedStmt.executeQuery() ;

                while (rs.next()){
                    Pair <Date, Integer> point = new Pair <Date, Integer>(rs.getDate(2), (int)rs.getFloat(3));
                    graph.points.add(point);
                }

                objectOutputStream.writeObject(graph);
                objectOutputStream.flush();


                str = bufferedReader.readLine();

                System.out.println(str);

                socket.close();




            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}