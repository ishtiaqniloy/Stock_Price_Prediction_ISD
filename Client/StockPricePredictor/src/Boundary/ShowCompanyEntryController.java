
package Boundary;

import Controller.PredictionViewController;
import static Controller.PredictionViewController.CEnlist;
import static Controller.PredictionViewController.table;
import Model.CompanyEntry;
import Model.ShareHistory;
import Model.SharePrediction;
import java.net.URL;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowCompanyEntryController implements Initializable {
    
    Random random;
     @FXML
    private Label enlistid;

    @FXML
    private Label tradecode;

    @FXML
    private Label stcode;

    @FXML
    private Label companyName;

    @FXML
    private Label lastTradingPrice;

    @FXML
    private TableView<ShareHistory> historyTable;

    @FXML
    private TableView<SharePrediction> predictionTable;

    @FXML
    private LineChart<String, Number> companyChart;
    
    @FXML
    private TableColumn<ShareHistory,String> enlistH;

    @FXML
    private TableColumn<ShareHistory,Integer> closingH;

    @FXML
    private TableColumn<ShareHistory,Date> dateH;

    @FXML
    private TableColumn<SharePrediction,String> enlistP;

    @FXML
    private TableColumn<SharePrediction,Date> dateP;

    @FXML
    private TableColumn<SharePrediction,Integer> futureClosingP;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<ShareHistory> SH=FXCollections.observableArrayList();
        ObservableList<SharePrediction> SP=FXCollections.observableArrayList();;
        CompanyEntry companyEntry=PredictionViewController.companyEntry;
        enlistid.setText(companyEntry.getEnlistID());
        tradecode.setText(companyEntry.getTradeCode());
        stcode.setText(companyEntry.getStCode());
        companyName.setText(companyEntry.getCompanyName());
        lastTradingPrice.setText(Integer.toString(companyEntry.getLastTradingPrice()));
        
        enlistH.setCellValueFactory(new PropertyValueFactory<>("enlistID"));
        dateH.setCellValueFactory(new PropertyValueFactory<>("date"));
        closingH.setCellValueFactory(new PropertyValueFactory<>("closingPrice"));
        
        enlistP.setCellValueFactory(new PropertyValueFactory<>("enlistID"));
        dateP.setCellValueFactory(new PropertyValueFactory<>("date"));
        futureClosingP.setCellValueFactory(new PropertyValueFactory<>("futureClosingPrice"));
        
        for(int i=0;i<companyEntry.getShareHistoryList().size();i++)
        {
            SH.add(companyEntry.getShareHistoryList().get(i));
        }
        for(int i=0;i<companyEntry.getSharePredList().size();i++)
        {
            SP.add(companyEntry.getSharePredList().get(i));
        }
        historyTable.getItems().setAll(SH);
        predictionTable.getItems().setAll(SP);
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        random = new Random();
        float f = random.nextFloat();
        
        System.out.println(f);
        //XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.getData().add(new XYChart.Data<>("JAN",f*IntegerRandom()));
        series1.getData().add(new XYChart.Data<>("FEB",f*IntegerRandom()));
        series1.getData().add(new XYChart.Data<>("MAR",f*IntegerRandom()));
        series1.getData().add(new XYChart.Data<>("APR",f*IntegerRandom()));
        series1.getData().add(new XYChart.Data<>("MAY",f*IntegerRandom()));
        series1.getData().add(new XYChart.Data<>("JUN",f*IntegerRandom()));
        series1.getData().add(new XYChart.Data<>("JUL",f*IntegerRandom()));
        series1.getData().add(new XYChart.Data<>("AUG",f*IntegerRandom()));
        series1.getData().add(new XYChart.Data<>("SEPT",f*IntegerRandom()));
        series1.getData().add(new XYChart.Data<>("OCT",f*IntegerRandom()));
        series1.getData().add(new XYChart.Data<>("NOV",f*IntegerRandom()));
        series1.getData().add(new XYChart.Data<>("DEC",f*IntegerRandom()));
//        series1.getData().add(new XYChart.Data<>("MAY",f*IntegerRandom()));
//        series1.getData().add(new XYChart.Data<>("JUN",f*1200.6));
//        series1.getData().add(new XYChart.Data<>("JUL",f*507.5));
//        series1.getData().add(new XYChart.Data<>("AUG",f*1260.3));
//        series1.getData().add(new XYChart.Data<>("SEPT",f*360));
//        series1.getData().add(new XYChart.Data<>("OCT",f*1120));
//        series1.getData().add(new XYChart.Data<>("NOV",f*1220));
//        series1.getData().add(new XYChart.Data<>("DEC",f*1520));
        companyChart.getData().add(series1);
        companyChart.setTitle("Company Share Prediction");
        
        
    }
    public int IntegerRandom(){
        random = new Random();
        int min = 500;
        int max = 1500;
        int x = random.nextInt(max-min+1) + min;
        //int x = ThreadLocalRandom.current().nextInt(500, 1500 + 1);;
        return x;
    }
    
}
