/**
 * AnnualReport created by Weihang Guo on MacBook in a2
 * 
 * Author: Weihang Guo(wguo63@wisc.edu)
 * Date:   @4.21
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


import java.io.IOException;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
/**
 * The Class AnnualReport.
 */
public class AnnualReport extends Application {
	
	private MilkData data;
	
	public AnnualReport() {
		super();
		data = new MilkData();
		try {
			data.readMilkData("csv/small/");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public AnnualReport(MilkData data) {
		this.data = data;
	}
 
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
 
    /**
     * Start.
     *
     * @param stage the stage
     */
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Annual Report");
        stage.setWidth(550);
        stage.setHeight(600);
        //create a label for title
        Label title = new Label("Annual Report");
        title.setFont(new Font("Arial", 20));
        
        
        //create a table to show the annual report
        TableView<AnnualMilk> table = new TableView<>();
        table.setEditable(false);
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
        
        //create a horizontal box
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        Label prompt = new Label("Year:");
        TextField year = new TextField();
        year.setMaxWidth(50);
        Button b = new Button("Confirm");
        hbox.getChildren().addAll(prompt, year, b);
        
        // Handler
        AnnualReportHandler handler = new AnnualReportHandler(b, table, year, data);
        // register event handler for button
        b.setOnAction(handler);
        
        //create a horizontal box for the orders
        HBox hbox2 = new HBox();
        hbox2.setSpacing(10);
        ToggleGroup group = new ToggleGroup();
        RadioButton order1 = new RadioButton("Ascending Order");
        order1.setToggleGroup(group);
        order1.addEventHandler(ActionEvent.ACTION, 
        		(e) -> {weight.setSortType(TableColumn.SortType.ASCENDING);
        		table.getSortOrder().add(weight);
        		table.sort();});
        RadioButton order2 = new RadioButton("Discending Order");
        order2.setToggleGroup(group);
        order2.addEventHandler(ActionEvent.ACTION, 
        		(e) -> {weight.setSortType(TableColumn.SortType.DESCENDING);
        		table.getSortOrder().add(weight);
        		table.sort();});
        hbox2.getChildren().addAll(order1, order2);
        
        
 
        //create a vbox to contain all the elements
        VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(title, hbox, hbox2, table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
 
    
} 