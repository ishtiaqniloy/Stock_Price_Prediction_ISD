package stockpricepredictor;

import Controller.PredictionViewController;
import Model.CompanyEnlistment;
import Model.CompanyEntry;
import Model.Graph;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MarketGetterClient implements Runnable {
    public static boolean wait = true;

    ObjectInputStream objectInputStream;
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    ArrayList<CompanyEnlistment> cenlist=new ArrayList<>();

    String code;

    public MarketGetterClient(String code) {
        this.code = code;
    }

    @Override
    public void run() {
        System.out.println("inside run-----");
        try {

            Socket socket = new Socket(StockPricePredictor.serverIP,4040);
            //socket.connect(socket.getRemoteSocketAddress());

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner socketScanner = new Scanner(socket.getInputStream());
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("connected");

            String str = "Send Market";

            printWriter.println(str);
            printWriter.flush();

            printWriter.println(code);
            printWriter.flush();



            Graph marketGraph = (Graph)objectInputStream.readObject();
            System.out.println(marketGraph);

            printWriter.println("Market Received");
            printWriter.flush();

            PredictionViewController.setMarketGraph(marketGraph);

            socket.close();


        } catch (IOException ex) {
            Logger.getLogger(ListGetterClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListGetterClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public ArrayList<CompanyEnlistment> getCEnlistment()
    {
        return cenlist;
    }
}
