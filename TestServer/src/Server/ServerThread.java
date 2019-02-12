package Server;

import Model.Company;
import Model.CompanyEnlistment;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

class ServerThread implements Runnable{
    Socket socket;
    InputStream inputStream;
    OutputStream outputStream;

    BufferedReader bufferedReader;
    PrintWriter printWriter;

    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    ServerThread(Socket socket){

        System.out.println("Connection accepted from " + socket.getInetAddress());

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
            while(true){

                str = bufferedReader.readLine();
                System.out.println(str);
                    if(str.equalsIgnoreCase("Send List")){
                        stmt = connection.createStatement();
                        String query2 = "select * from spp_db.`company enlistment`;" ;
                        ResultSet rs = stmt.executeQuery(query2) ;
                        int j = 0;



                        ArrayList<CompanyEnlistment> companyEnlistments = new ArrayList<CompanyEnlistment>();

                        while(rs.next()){

                            while(rs.next()){
                                System.out.println((j++) + " " + rs.getString(1)+ " " + rs.getString(2)+ " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getDate(6));
                                CompanyEnlistment companyEnlistment = new CompanyEnlistment(rs.getString(1), rs.getString(2),rs.getString(3) , (int)Float.parseFloat(rs.getString(5)), rs.getDate(6), (int)Float.parseFloat(rs.getString(4)));

                                companyEnlistments.add(companyEnlistment);
                            }

                        }

                        System.out.println(companyEnlistments.size());
                        objectOutputStream.writeObject(companyEnlistments.size());
                        objectOutputStream.flush();

//                        for (CompanyEnlistment companyEnlistment: companyEnlistments) {
//                            System.out.println("sending: " + companyEnlistment );
//
//                            objectOutputStream.writeObject(companyEnlistment);
//                            objectOutputStream.flush();
//                        }
//
//
//
//                        str = bufferedReader.readLine();
//
//                        System.out.println(str);


                    }

            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}