package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

    public class Server {
        public static void main(String []args){
            try{
                ServerSocket serverSocket = new ServerSocket(4040);
                System.out.println("Server has been started successfully. Listening...");

                while(true) {
                    Socket s = serverSocket.accept();        //TCP Connection
                    ServerThread wt = new ServerThread(s);
                    Thread t = new Thread(wt);
                    t.start();
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }

    }

