/** Main.java created by junxuanzhang on MacBook Air in ateam
 *  
 *  Author: 	Junxuan Zhang(jzhang2329@wisc.edu)
 *  Date: 		@date
 *  
 *  Course: 	CS400
 *  Semester: 	Spring 2020
 *  Lecture: 	001
 *  
 *  IDE:		Eclipse IDE for Java Developers
 *  Version: 	2019-12(4.14.0)
 *  Build id: 	20191212-1212
 *  
 *  Device: 	Macbook Air
 *  OS: 		macOS Catalina
 *  Version: 	10.15
 *  
 *  List Collaborators:N/A
 *  
 *  Other Credits: N/A
 *  
 *  Known Bugs: N/A
 */

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Main - TODO Describe purpose of this user-defined type
 * @author zhang (2020)
 *
 */
public class DateRangeReport extends Application {
	private static final int WINDOW_WIDTH = 840;
	private static final int WINDOW_HEIGHT = 600;
	private static final String title = "Date Range Report";
	private static final String DRR = "Date Range Report";
	private static final String Start = "Start Date (Year-Month-Day)";
	private static final String End = "End Date (Year-Month-Day)";
	private static final String AOrder = "Ascending Order";
	private static final String DOrder = "Decsending Order";
	private static final String confirm = "Confirm";

	private static final java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//*****************
//    	BorderPane root = new BorderPane();
//    	VBox vbox = new VBox();
//    	HBox hbox1 = new HBox();
//    	HBox hbox2 = new HBox();
//    	HBox hbox3 = new HBox();
//
//    	HBox hbox4 = new HBox();
//
//    	VBox vbox4 = new VBox();
//
//    	TextField text = new TextField();
//    	text.setPromptText("2020");
//    	TextField text2 = new TextField();
//    	text2.setPromptText("2020");
//
//    	CheckBox checkBox1 = new CheckBox();
//    	CheckBox checkBox2 = new CheckBox();
//
//   // 	Pane pane = new Pane();
//    	
//		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
//
//	
//		
//		
//		
//		
//		
//	//	text.setPromptText("asdf");
//		
//		//top
//		Label report = new Label(DRR);
//		report.setFont(Font.font("Arial",FontWeight.BOLD,20));
//	//	report.setPrefWidth(300); 
//
//		//*********
//		//root.setTop(report);
//		
//		
//		//Center
//		Label start = new Label(Start);
//		hbox1.getChildren().addAll(start,text);
//		
//		String gt = null;
////		gt = text.getText();
//		text.getText();
//		//Label tg = new Label(gt);
//
//		
//		Label end = new Label(End);
//		hbox2.getChildren().addAll(end,text2);
//		
//		Label AO = new Label(AOrder);
//		Label DO = new Label(DOrder);
//		Label space = new Label("       ");
//		Label space1 = new Label("       ");
//		Label space2 = new Label("       ");
//		Label space3 = new Label("       ");
//		Label space4 = new Label("       ");
//		Label space5 = new Label("       ");
//		Label space6 = new Label("       ");
//		
//	//	hbox3.getChildren().addAll(AO,checkBox1,space,DO,checkBox2);
//		vbox.getChildren().addAll(space,hbox1,space1,space2,space4,hbox2,space3,space5);
//		
//		
//		
//		Label space10 = new Label("                                                                          ");
//
//		
//		//Right
//        Button c = new Button ("Confrim");
//        //hbox4.getChildren().addAll(c,space);
//      //  c.setPadding(new Insets(20));
//        hbox4.getChildren().addAll(c,space10);
////        vbox4.setAlignment(Pos.CENTER);
//		root.setRight(hbox4);
//	
//		//*******
//		//Handler
//	//	DateMyHandler handler = new DateMyHandler(c,root,text);
//		
//		//*********
//		//register event handler for button
//		//c.setOnAction(handler);
//		
//		//*****
//		//root.setCenter(vbox);
//		
//		
////		Label report1 = new Label(Start);
////		root.setCenter(report1);
//		
//		
//		pane.setLayoutX(100);
//		pane.setLayoutY(100);
		
		
		
		
		
		
		
		//***********
		VBox vbox = new VBox();
		
    	HBox hbox1 = new HBox();
    	HBox hbox2 = new HBox();	
    	
    	TextField text1 = new TextField();//YYYY/MM/DD
 //   	text1.setEditable(false);
    	TextField text2 = new TextField();//YYYY/MM/DD
 //   	text2.setEditable(false);

//    	String st = text1.toString.substring(0,1);

		Label report = new Label(title);
		Label start = new Label(Start);
		Label end = new Label(End);
	
//	    DatePicker checkInDatePicker = new DatePicker();
//	    checkInDatePicker.setEditable(false);
//	    DatePicker checkOutDatePicker = new DatePicker();
//	    checkOutDatePicker.setEditable(false);

//	    LocalDate date = checkInDatePicker.setValue(arg0);;
	    
		report.setFont(Font.font("Arial",FontWeight.BOLD,20));
		
		//hbox
		hbox1.getChildren().addAll(start,text1);
		hbox2.getChildren().addAll(end,text2);

		//vbox
		vbox.getChildren().addAll(report,hbox1,hbox2);
		vbox.setSpacing(20);
		vbox.setPadding(new Insets(10, 0, 0, 10));

		//confirm button
		Button confirm = new Button("Confrim");
		confirm.setLayoutX(600);
		confirm.setLayoutY(75);
		confirm.setPrefWidth(75);
	//	confirm.setPrefHeight(35);

		
		MilkData map = new MilkData();
		map.readMilkData("csv/small/");
		
		//Table
		//add table
		 TableView table=new TableView<>(); 
		 table.setEditable(true);
		 
		 //1
		 TableColumn col1=new TableColumn<>("Date");
		 col1.setPrefWidth(200);
		 col1.setCellValueFactory(
				 new PropertyValueFactory<>("date") );
		 
		 //2
		 TableColumn col2=new TableColumn<>("Farm ID"); 
		 col2.setPrefWidth(200); 
		 col2.setCellValueFactory(
				 new PropertyValueFactory<>("farmID") );
		 
		 //3
		 TableColumn col3=new TableColumn<>("Total Weight"); 
		 col3.setPrefWidth(200); 
		 col3.setCellValueFactory(
				 new PropertyValueFactory<>("weight") );
		 
		 
		 //4
		 TableColumn col4=new TableColumn<>("Percent of Total Weight of All Farms"); 
		 col4.setPrefWidth(200); 
		 col4.setCellValueFactory(
				 new PropertyValueFactory<>("percentage") );
		 
		//table.setItems(data);
		table.getColumns().addAll(col1,col2,col3,col4);	
		table.setLayoutY(150);
		table.setPadding(new Insets(0, 20, 0, 20));
		
		

		
		//Group
		Group group = new Group();
		group.getChildren().addAll(vbox,confirm, table);
		
		
		
		Scene mainScene = new Scene(group);
		
		
		//Handler
		DateMyHandler handler = new DateMyHandler(confirm,table,text1,text2);

		confirm.setOnAction(handler);
		
		

		
		
//*********************************8	
//		confirm.setOnAction(new EventHandler<ActionEvent>() {
//			/**
//			 * Handle actions.
//			 *
//			 * @param event the event
//			 */
//			@Override
//			public void handle(ActionEvent event) {
//				Pane centerPane = DataManagerPane.getDataManagerPane();
//				root.setCenter(centerPane);
//				BorderPane.setMargin(centerPane, insets);
//			}
//		});
//************************************8		
		primaryStage.setHeight(600);
		primaryStage.setWidth(840);
		primaryStage.setTitle(title);
    	primaryStage.setScene(mainScene);
    	primaryStage.show();
	}
	
	
	public class DateMyHandler implements javafx.event.EventHandler<ActionEvent> {
		private String A = "2020";
		private Object primaryStage;
		private TableView table;
		private Button button;
		private TextField text1;
		private TextField text2;
		private DatePicker checkInDatePicker;
		private DatePicker checkOutDatePicker;

		


//		private final ObservableList<Data> data =
//				FXCollections.observableArrayList(new Data(A,"001","100","20")
//
//					 );
		
		public DateMyHandler(Button confirm,TableView table, TextField text1, TextField text2 ) {
			this.button = confirm; 
			this.table = table;
			this.checkInDatePicker = checkInDatePicker;
			this.checkInDatePicker = checkOutDatePicker;
			this.text1 = text1;
//			this.date = date;

		}
		
		
		private ObservableList<Data> extract(TextField text1, TextField text2, MilkData map) {
			
		
			
			ObservableList<Data> oneMonthData = FXCollections.observableArrayList();
			TreeMap<String, TreeMap<String, TreeMap<String, Integer>>> allMonth = map.getMilkMap();
			
			return null;

		}
		
		
		
		


		
		
		
		
		
		
		
		
		
		@Override
		public void handle(ActionEvent arg0) {
//			LocalDate localDate = checkInDatePicker.getValue();
//			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
//			Date date = Date.from(instant);
//			System.out.println(localDate + "\n" + instant + "\n" + date);
//			table.setItems(data);

//			String date = checkInDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//			String k = "1234567890";
//			LocalDate day = checkInDatePicker.getValue();
	//		if(day == checkInDatePicker.getValue())
//				System.out.println(day);

			
//			String r = "csv/small/";
//
//			
//			
//			MilkData milk = new MilkData();
//			try {
//				milk.readMilkData(r);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			System.out.println(k.substring(0,k.length()-4));
//
//


			
			
			
			
			
			
			
			
			
			
		}
//&&&&&&&&&&&&&&&&&&&&&&&&&
		public class Data{
			private final SimpleStringProperty Date;
			private final SimpleStringProperty FarmID;
			private final SimpleIntegerProperty Weight;
			private final SimpleDoubleProperty Percentage;
			
//			private Data(String date,String farmID,String weight, String percentage ) {
//				this.Date = new SimpleStringProperty(date);
//				this.FarmID = new SimpleStringProperty(farmID);
//				this.Weight = new SimpleStringProperty(weight);
//				this.Percentage = new SimpleStringProperty(percentage);
//
//			}
			
			public Data(String Data, int FarmID,int Weight, double Percentage) {
				this.Date = new SimpleStringProperty();
				this.FarmID = new SimpleStringProperty();
				this.Percentage = new SimpleDoubleProperty();
				this.Weight = new SimpleIntegerProperty();
				// TODO Auto-generated constructor stub
				
			}

			public String getDate() {
				return Date.get();
			}
			public void setDate(String date) {
				 Date.set(date);
			}
			
			public String getFarmID() {
				return FarmID.get();
			}
			public void setFarmID(String farmID) {
				Date.set(farmID);
			}
			
			public Integer getWeight() {
				return Weight.get();
			}
			public void setWeight(String weight) {
				Date.set(weight);
			}
			
			public Double getPercentage() {
				return Percentage.get();
			}
			public void setPercentage(String percentage) {
				Date.set(percentage);
			}
	}

	}


//	import java.time.Instant;
//	import java.time.LocalDate;
//	import java.time.ZoneId;
//	import java.util.Date;
//	import javafx.scene.control.DatePicker;
//	public class Main {
	
//	    public static Date getDateFromDatePicket(DatePicker datePicker) {
//	        LocalDate localDate = datePicker.getValue();
//	        if (localDate != null) {
//	            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId
//	                    .systemDefault()));
//	            Date date = Date.from(instant);
//	            return date;
//	        } else {
//	            return null;
//	        }
//	    }
//	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   launch(args);

	}
	
	
	
	
	
	
	

}
