package Controller;

import Boundary.ShowCompanyEntryController;
import Model.CompanyEnlistment;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.scene.chart.CategoryAxis;
import Model.CompanyEntry;
import Model.Graph;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import stockpricepredictor.EntryGetterClient;
import stockpricepredictor.ListGetterClient;
import stockpricepredictor.MarketGetterClient;
import stockpricepredictor.SearchedCompanyClient;

public class PredictionViewController implements Initializable {

    public static ObservableList<CompanyEnlistment> CEnlist;
    public static TableView<CompanyEnlistment> table;
    public static LineChart<String, Number> staticMarketdetails;
    public static VBox staticVboxinsidescroll;
    public static Graph marketGraph;
    public static CompanyEntry companyEntry;
    private static boolean flag = false;

    public static Graph getMarketGraph() {
        return marketGraph;
    }

    public static CompanyEntry getCompanyEntry() {
        return companyEntry;
    }

    public static void setCompanyEntry(CompanyEntry companyEntry) throws IOException {
        ///HERE OPEN A NEW WINDOW
        PredictionViewController.companyEntry = companyEntry;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ShowCompanyEntryController.class.getResource("showCompanyEntry.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(PredictionViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("insert book");
                stage.setScene(scene);
                stage.show();
            }
        });

    }
    
    @FXML
    private JFXButton backButton;

    @FXML
    private TextField searchBar;

    @FXML
    private LineChart<String, Number> marketdetails;

    @FXML
    private VBox listvbox;

    @FXML
    private VBox vboxinsidescroll;
    @FXML
    private JFXButton SerachButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //staticMarketdetails = marketdetails;
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        //XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.getData().add(new XYChart.Data<>("JAN",10.2));
        series1.getData().add(new XYChart.Data<>("FEB",20.1));
        series1.getData().add(new XYChart.Data<>("MAR",15.1));
        series1.getData().add(new XYChart.Data<>("APR",31.5));
        series1.getData().add(new XYChart.Data<>("MAY",35.5));
        series1.getData().add(new XYChart.Data<>("JUN",25.6));
        series1.getData().add(new XYChart.Data<>("JUL",42));
        series1.getData().add(new XYChart.Data<>("AUG",40.5));
        series1.getData().add(new XYChart.Data<>("SEPT",37.5));
        series1.getData().add(new XYChart.Data<>("OCT",43.5));
        series1.getData().add(new XYChart.Data<>("NOV",30.2));
        series1.getData().add(new XYChart.Data<>("DEC",29.5));
        marketdetails.getData().add(series1);
        marketdetails.setTitle("Market Prediction");
        //marketdetails.getData().add(series1);

        CEnlist = FXCollections.observableArrayList();

//     if (!vboxinsidescroll.getChildren().isEmpty()) {
//            vboxinsidescroll.getChildren().clear();
//        }
        table = new TableView<>();

        TableColumn<CompanyEnlistment, String> enlistid = new TableColumn<>("ENLIST ID");
        enlistid.setMinWidth(100);
        enlistid.setCellValueFactory(new PropertyValueFactory<>("enlistID"));

        TableColumn<CompanyEnlistment, String> tradecode = new TableColumn<>("TRADE CODE");
        tradecode.setMinWidth(100);
        tradecode.setCellValueFactory(new PropertyValueFactory<>("TradeCode"));

        TableColumn<CompanyEnlistment, String> stcode = new TableColumn<>("ST CODE");
        stcode.setMinWidth(100);
        stcode.setCellValueFactory(new PropertyValueFactory<>("StCode"));

        TableColumn<CompanyEnlistment, Integer> lastTprice = new TableColumn<>("LAST TRADING PRICE");
        lastTprice.setMinWidth(150);
        lastTprice.setCellValueFactory(new PropertyValueFactory<>("LastTradingPrice"));

        TableColumn<CompanyEnlistment, Date> enlistdate = new TableColumn<>("ENLIST DATE");
        enlistdate.setMinWidth(200);
        enlistdate.setCellValueFactory(new PropertyValueFactory<>("enlistDate"));

        TableColumn<CompanyEnlistment, Integer> initprice = new TableColumn<>("INITIAL PRICE");
        initprice.setMinWidth(150);
        initprice.setCellValueFactory(new PropertyValueFactory<>("initialPrice"));

        System.out.println("before adding---");
        table.getColumns().addAll(enlistid, tradecode, stcode, lastTprice, enlistdate, initprice);
        //   CEnlist.add(new CompanyEnlistment("hi","hello","st", 100,new Date(),50));
        //CEnlist=getEnlistment();
        table.getItems().setAll(CEnlist);
        // System.out.println("added");
        staticVboxinsidescroll = vboxinsidescroll;
        vboxinsidescroll.getChildren().addAll(table);

        // double click-------------
        table.setRowFactory(tv -> {
            TableRow<CompanyEnlistment> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    CompanyEnlistment clickedRow = row.getItem();
                    CompanyEnlistment temp;
                    temp = clickedRow;
                    System.out.println(temp);

                    EntryGetterClient entryGetterClient = new EntryGetterClient(temp.getEnlistID());
                    Thread et = new Thread(entryGetterClient);
                    et.start();

                }
            });
            return row;
        });

        ListGetterClient listGetterClient = new ListGetterClient();
        Thread lt = new Thread(listGetterClient);
        lt.start();

        MarketGetterClient marketGetterClient = new MarketGetterClient("NASDAQ");
        Thread mt = new Thread(marketGetterClient);
        mt.start();

    }

    synchronized public static void setMarketGraph(Graph marketGraph) {
        PredictionViewController.marketGraph = marketGraph;

//        XYChart.Series<Integer, Integer> series1 = new XYChart.Series<>();
//        int i = 1;
//        series1.getData().add(new XYChart.Data<>(1, 10));
//        series1.getData().add(new XYChart.Data<>(2, 20));
//
//        for (Pair<Date, Integer> point : marketGraph.points) {
//            //System.out.println(point.getKey().getTime() + " " + point.getValue());
////            series1.getData().add(new XYChart.Data<>(i, i));
////            i++;
//        }
//        flag = true;
//
//        staticMarketdetails.getData().add(series1);
        System.out.println("list");

    }

    synchronized public static void setTable(ArrayList<CompanyEnlistment> companyEnlistments) {
        CEnlist.clear();
        for (CompanyEnlistment companyEnlistment : companyEnlistments) {
            CEnlist.add(companyEnlistment);
        }
//        CEnlist.add(new CompanyEnlistment("hi","hello","st", 100,new Date(),50));
        //CEnlist=getEnlistment();

        table.getItems().setAll(CEnlist);
        System.out.println("added");
//        staticVboxinsidescroll.getChildren().addAll(table);

    }

    public ObservableList<CompanyEnlistment> getEnlistment() {
        ObservableList<CompanyEnlistment> CEntries = FXCollections.observableArrayList();
        ViewerController controller = null;
        ArrayList<CompanyEnlistment> arraylist = controller.getEnlistList();
        for (int i = 0; i < arraylist.size(); i++) {
            CEntries.add(arraylist.get(i));
        }
        System.out.println("copying done");
        return CEntries;
    }

    @FXML
    void handleSearchByCode(ActionEvent event) {
        String searchText=searchBar.getText();
        searchBar.clear();
        SearchedCompanyClient searchedCompanyClient = new SearchedCompanyClient(searchText);
        Thread st = new Thread(searchedCompanyClient);
        st.start();
    }
      @FXML
    void fetchMainList(ActionEvent event) {
          setTable(ListGetterClient.maincenlist);
    }

}
