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
 * AnnualReportHandler created by Weihang Guo on MacBook in a3
 * 
 * Author: Weihang Guo(wguo63@wisc.edu)
 * Date:   @4.29
 * 
 * Course: CS400
 * Semester: Spring 2020
 * Lecture: 002
 * 
 * IDE: Eclipse IDE for Java Developers
 * Version: 2019-12(4.14.0)
 * Build id: 20191212-1212
 * 
 * Device: LisaG's MACBOOK
 * OS: macOS Mojave
 * Version: 10.14.4
 * OS Build: 1.8 GHz Intel Core i5
 * 
 * List Collaborators: None
 * 
 * Other Credits: None
 * 
 * Known Bugs: None
 */


/**
 * FarmReportHandler 
 * 
 * @author Weihang, Zhang
 *
 */

public class AnnualReportHandler implements javafx.event.EventHandler<ActionEvent> {
	
  private Button button;
	
  private TableView table;

  private TextField year;

  private final ObservableList<MilkItem> datalist = FXCollections.observableArrayList();

  /**
   * Instantiates a new annual range handler.
   *
   * 
   */
  public AnnualReportHandler(Button button, TableView<AnnualMilk> table, TextField year) {
	this.button = button;
    this.table = table;
    this.year = year;
  }


  @Override
  public void handle(ActionEvent arg0) {
	  
	  
	  
    // retrieve data
    String farmYear = year.getText().toString();

    MilkData data = new MilkData();

    ObservableList<FarmReport.Milk> tableitems = FXCollections.observableArrayList();

    try {
      data.readMilkData(DataManagerPane.dir);
      Set<String> ids = data.getFarmIDList();
      List<MilkItem> items = data.getDataByYear(farmYear);
      int weightOfAllFarms = 0;
      for (MilkItem item : items) {
		  weightOfAllFarms += item.getWeight();
      }
	  
      for (String id : ids) {
    	  
    	  int annualWeight = 0;
    	  
    	  for (MilkItem item : items) {
    		  
    		  if (item.getFarmID() == id) {
    			  annualWeight += item.getWeight();
    		  }
    		  
    	  }
    	  //find percentage
    	  double percent = (double)annualWeight/(double)weightOfAllFarms;
    	  //add to table
    	  AnnualMilk tableitem = new AnnualMilk(id, annualWeight, percent);
      }

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }




    
    //build the farmID column
    TableColumn farmID = new TableColumn("FarmID");
    farmID.setMinWidth(100);
    farmID.setCellValueFactory(new PropertyValueFactory<>("farmID"));

    //build the weight column
    TableColumn weight = new TableColumn("Total Weight");
    weight.setMinWidth(100);
    weight.setCellValueFactory(new PropertyValueFactory<>("weight"));

    //build the percentage column
    TableColumn percentage = new TableColumn("Percent of Total Weight of All Farms");
    percentage.setMinWidth(300);
    percentage.setCellValueFactory(
            new PropertyValueFactory<>("percentage"));
    table.getColumns().addAll(farmID, weight, percentage);
    
    
    

    table.setItems(datalist);
    table.getColumns().addAll(farmID, weight, percentage);

    table.setItems(tableitems);

  }


}