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

  private Button button;

  private VBox root;

  private TextField id;

  private TextField year;

  private final ObservableList<Data> data = FXCollections.observableArrayList();

  /**
   * Instantiates a new date range handler.
   *
   * 
   */
  public FarmReportHandler(Button c, VBox root, TextField id, TextField year) {
    button = c;
    this.root = root;
    this.id = id;
    this.year = year;
  }


  @Override
  public void handle(ActionEvent arg0) {
    // TODO Auto-generated method stub
    // retrieve data
    String farmid = id.getText().toString();
    String farmyear = year.getText().toString();

    // add table
    TableView table = new TableView<>();
    table.setEditable(true);

    // 1
    TableColumn col1 = new TableColumn<>("Month");
    col1.setPrefWidth(250);
    col1.setCellValueFactory(new PropertyValueFactory<>("month"));

    // 2
    TableColumn col2 = new TableColumn<>("Farm ID");
    col2.setPrefWidth(250);
    col2.setCellValueFactory(new PropertyValueFactory<>("farmID"));


    // 3
    TableColumn col3 = new TableColumn<>("Percent of Total Weight of All Farms");
    col3.setPrefWidth(250);
    col3.setCellValueFactory(new PropertyValueFactory<>("percentage"));

    table.setItems(data);
    table.getColumns().addAll(col1, col2, col3);


    root.setBottom(table);

  }

  /**
   * The Class Data.
   */
  public static class Data {

    /** The Date. */
    private final SimpleStringProperty Year;

    /** The Farm ID. */
    private final SimpleStringProperty FarmID;

    /** The Percentage. */
    private final SimpleStringProperty Percentage;

    /**
     * Instantiates new data.
     *
     */
    private Data(String year, String farmID, String percentage) {
      this.Year = new SimpleStringProperty(year);
      this.FarmID = new SimpleStringProperty(farmID);
      this.Percentage = new SimpleStringProperty(percentage);

    }

    /**
     * Gets the date.
     *
     * @return the date
     */
    public String getDate() {
      return Year.get();
    }

    /**
     * Sets the date.
     *
     * @param date the new date
     */
    public void setDate(String date) {
      Year.set(date);
    }

    /**
     * Gets the farm ID.
     *
     * @return the farm ID
     */
    public String getFarmID() {
      return FarmID.get();
    }

    /**
     * Sets the farm ID.
     *
     * @param farmID the new farm ID
     */
    public void setFarmID(String farmID) {
      Year.set(farmID);
    }

    /**
     * Gets the percentage.
     *
     * @return the percentage
     */
    public String getPercentage() {
      return Percentage.get();
    }

    /**
     * Sets the percentage.
     *
     * @param percentage the new percentage
     */
    public void setPercentage(String percentage) {
      Year.set(percentage);
    }
  }

}
