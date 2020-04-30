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
 * @author Weihang
 *
 */

public class AnnualReportHandler implements javafx.event.EventHandler<ActionEvent> {
	
	private MilkData data;
	
	private Button button;
	
	private TableView table;

	private TextField year;
  /**
   * Instantiates a new annual range handler.
   *
   * 
   */
  public AnnualReportHandler(Button button, TableView<AnnualMilk> table, TextField year, MilkData data) {
	this.button = button;
    this.table = table;
    this.year = year;
    this.data = data;
  }


  @Override
  public void handle(ActionEvent arg0) {
	  
	  
	  
	  List<AnnualMilk> list = new ArrayList<>();
	  
	  // retrieve data
	  String farmYear = year.getText().toString();

	  ObservableList<FarmReport.Milk> tableitems = FXCollections.observableArrayList();
	  
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
			  list.add(tableitem);
		  }



	  table.getItems().clear();
	  table.getItems().addAll(list);
	  }


}