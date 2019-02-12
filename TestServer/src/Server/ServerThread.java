package Server;

import Model.Company;

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



                        ArrayList<Company> companies = new ArrayList<>();

                        while(rs.next()){

                            System.out.println((j++) + " " + rs.getString(1)+ " " + rs.getString(2)+ " " + rs.getString(3)+ " " + rs.getString(4)+ " " + rs.getString(5)+ " " + rs.getString(6));


                            Company company = new Company(rs.getString(1), rs.getString(2),Integer.parseInt(rs.getString(3)) , Integer.parseInt(rs.getString(4)), Integer.parseInt(rs.getString(5)), rs.getString(6));

                            companies.add(company);

                        }

                        System.out.println(companies.size());
                        printWriter.print(companies.size());
                        printWriter.flush();

                        for (Company company: companies) {
                            System.out.println("sending: " + company );

                            objectOutputStream.writeObject(company);
                            objectOutputStream.flush();
                        }



                        str = bufferedReader.readLine();

                        System.out.println(str);


                    }

            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}