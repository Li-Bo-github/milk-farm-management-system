/**
 * Main.java created by Bo
 *
 * Author: Bo Li (bli379@wisc.edu) Date: 4/21/2020
 *
 * 
 * Course: CS400 Semester: Spring 2020 Lecture: 001
 * 
 * IDE: Eclipse IDE For Java Developers Version: 291942 (4.14.0)
 */


/**
 * Main
 * 
 * @author Bo
 *
 */
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FarmReport extends Application {
  // store any command-line arguments that were entered.
  // NOTE: this.getParameters().getRaw() will get these also
  // private List<String> args;

  private static final int WINDOW_WIDTH = 840;
  private static final int WINDOW_HEIGHT = 600;
  private static final String APP_TITLE = "Farm Report";

  @Override
  public void start(Stage primaryStage) throws Exception {
    // save args example
    // args = this.getParameters().getRaw();

    // title
    Label title = new Label("Farm Report");
    title.setFont(new Font("Arial", 20));

    // create a horizontal box
    HBox hbox1 = new HBox();
    HBox hbox2 = new HBox();
    hbox1.setSpacing(10);
    hbox2.setSpacing(10);

    // create a vbox
    VBox vbox = new VBox();
    vbox.setSpacing(20);

    Label prompt1 = new Label("Farm ID:");
    Label prompt2 = new Label("Year:     ");
    TextField farmid = new TextField();
    TextField year = new TextField();
    farmid.setPromptText("Type ID here");
    year.setPromptText("Type Year here");

    farmid.setMaxWidth(300);
    year.setMaxWidth(300);


    // create a table
    TableView<Milk> table = new TableView<>();


    // build the month column
    TableColumn month = new TableColumn("Month");
    month.setMinWidth(100);
    month.setCellValueFactory(new PropertyValueFactory<>("month"));

    // build the total weight column
    TableColumn totalWeight = new TableColumn("Total Weight");
    totalWeight.setMinWidth(100);
    totalWeight.setCellValueFactory(new PropertyValueFactory<>("totalWeight"));

    // build the percentage column
    TableColumn percentage = new TableColumn("Percent of Total Weight");
    percentage.setMinWidth(300);
    percentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));

    // add to table
    table.getColumns().addAll(month, totalWeight, percentage);


    Button confirm = new Button("Confirm");

    // Handler
    FarmReportHandler handler = new FarmReportHandler(confirm, vbox, farmid, year);

    // register event handler for button
    confirm.setOnAction(handler);

    // action
    ObservableList<Milk> data = FXCollections.observableArrayList();
    confirm.addEventHandler(ActionEvent.ACTION, (e) -> table.setItems(data));

    // add to hbox
    hbox1.getChildren().addAll(prompt1, farmid);
    hbox2.getChildren().addAll(prompt2, year, confirm);


    vbox.setPadding(new Insets(10, 0, 0, 10));
    vbox.getChildren().addAll(title, hbox1, hbox2, table);

    Scene mainScene = new Scene(vbox, WINDOW_WIDTH, WINDOW_HEIGHT);

    // set css
    mainScene.getStylesheets().add(getClass().getResource("FarmReportStyle.css").toExternalForm());

    // Add the stuff and set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }


  public static class Milk {

    private final SimpleStringProperty farmID;
    private final SimpleIntegerProperty weight;
    private final SimpleDoubleProperty percentage;

    private Milk(String farmID, Integer weight, Double percentage) {
      this.farmID = new SimpleStringProperty(farmID);
      this.weight = new SimpleIntegerProperty(weight);
      this.percentage = new SimpleDoubleProperty(percentage);
    }

    public String getFarmID() {
      return farmID.get();
    }


    public Integer getWeight() {
      return weight.get();
    }

    public Double getPercentage() {
      return percentage.get();
    }

  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}

