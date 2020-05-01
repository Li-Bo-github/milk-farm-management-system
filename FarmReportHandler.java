import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FarmReportHandler.java created by Bo in ateam
 *
 * Author: Bo Li (bli379@wisc.edu) Date: @date
 *
 * 
 * Course: CS400 Semester: Spring 2020 Lecture: 001
 * 
 * IDE: Eclipse IDE For Java Developers Version: 291942 (4.14.0)
 */

/**
 * FarmReportHandler - TODO Describe purpose of this user-defined type
 * 
 * @author Bo, zhang
 *
 */
public class FarmReportHandler implements javafx.event.EventHandler<ActionEvent> {

  private MilkData milkData;

  private VBox root;

  private TextField id;

  private TextField year;
  

  private ObservableList<FarmReport.Milk> tableitems =
      FXCollections.observableArrayList();


  /**
   * Instantiates a new date range handler.
   *
   * 
   */
  public FarmReportHandler(MilkData milkData, VBox root, TextField id, TextField year) {
    this.milkData = milkData;
    this.root = root;
    this.id = id;
    this.year = year;
  }


  @Override
  public void handle(ActionEvent arg0) {
    // TODO Auto-generated method stub
    // retrieve data
    EventLog.getInstance().log("FarmReport Generated");
    String farmid = id.getText().toString();
    String farmyear = year.getText().toString();


    Set<String> months = milkData.getMonths();

    for (String month : months) {
      // check year
      String[] arrOfStr = month.split("-", 2);
      if (arrOfStr[0].equals(year)) {
        List<MilkItem> itemlist = milkData.getDataByMonth(month);

        int totalofall = 0;
        int totaloffarm = 0;

        // find id
        for (MilkItem item : itemlist) {
          totalofall += item.getWeight();

          if (item.getFarmID().equals(id)) {
            totaloffarm += item.getWeight();
          }
        }

        // find percentage
        double percent = totaloffarm / totalofall;

        // add to table
        FarmReport.Milk tableitem =
            new FarmReport.Milk(arrOfStr[1], (Integer) totaloffarm, (Double) percent);
        tableitems.add(tableitem);
      }

    }



    // add table
    TableView table = new TableView<>();
    table.setEditable(true);

    // 1
    TableColumn<FarmReport.Milk, String> col1 = new TableColumn<>("Month");
    col1.setPrefWidth(250);
    col1.setCellValueFactory(new PropertyValueFactory<FarmReport.Milk, String>("month"));

    // 2
    TableColumn<FarmReport.Milk, Integer> col2 = new TableColumn<>("Weight");
    col2.setPrefWidth(250);
    col2.setCellValueFactory(new PropertyValueFactory<FarmReport.Milk, Integer>("weight"));


    // 3
    TableColumn<FarmReport.Milk, Double> col3 =
        new TableColumn<>("Percent of Total Weight of All Farms");
    col3.setPrefWidth(250);
    col3.setCellValueFactory(new PropertyValueFactory<FarmReport.Milk, Double>("percentage"));

    table.setItems(tableitems);
    table.getColumns().addAll(col1, col2, col3);

    root.getChildren().add(table);

  }


}
