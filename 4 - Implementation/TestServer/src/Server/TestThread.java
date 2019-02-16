package Server;

import Model.Company;
import Model.CompanyEnlistment;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

class TestThread implements Runnable{
    Socket socket;
    InputStream inputStream;
    OutputStream outputStream;

    BufferedReader bufferedReader;
    PrintWriter printWriter;

    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    TestThread(Socket socket){

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
            while(true){

                str = bufferedReader.readLine();
                System.out.println(str);
                if(str.equalsIgnoreCase("Send List")){
                    printWriter.print("Hello World");


                }

            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}