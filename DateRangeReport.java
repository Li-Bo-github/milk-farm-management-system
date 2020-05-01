
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
 * 
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

	/** The milk data. */
	private MilkData milkData;

	public DateRangeReport() {
		super();
		this.milkData = new MilkData();
		try {
			this.milkData.readMilkData("csv/small/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public DateRangeReport(MilkData mData) {
		this.milkData = mData;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox vbox = new VBox();
		HBox hbox1 = new HBox();
		hbox1.setPrefHeight(25);
		hbox1.setSpacing(5);
		HBox hbox2 = new HBox();
		hbox2.setPrefHeight(25);
		hbox2.setSpacing(5);
		/*
		 * YYYY/MM/DD TextField text1 = new TextField(); YYYY/MM/DD TextField text2 =
		 * new TextField();
		 */
		DatePicker startPicker = new DatePicker();
		DatePicker endPicker = new DatePicker();
		Label report = new Label(title);
		Label start = new Label(Start);
		Label end = new Label(End);
		report.setFont(Font.font("Arial", FontWeight.BOLD, 20));

		// hbox
		hbox1.getChildren().addAll(start, startPicker);
		hbox2.getChildren().addAll(end, endPicker);

		// vbox
		vbox.getChildren().addAll(report, hbox1, hbox2);
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(10, 0, 0, 10));

		// confirm button
		Button confirm = new Button("Confirm");
		confirm.setLayoutX(500);
		confirm.setLayoutY(75);
		confirm.setPrefWidth(75);

		// Table
		// add table
		TableView<DataRangeItem> table = new TableView<>();
		table.setEditable(false);
		
		Button bt_export = new Button("Export");
		bt_export.setLayoutX(585);
		bt_export.setLayoutY(75);
		bt_export.setPrefWidth(75);
		
    bt_export.setOnAction(action -> {
      String rpName = export2File(table);
      String temp = "export to " + rpName;
      new Alert(Alert.AlertType.NONE, temp, new ButtonType[] { ButtonType.CLOSE }).show();
      EventLog.getInstance().log(temp);
    });

		// 1
		TableColumn col1 = new TableColumn<>("Date");
		col1.setPrefWidth(150);
		col1.setCellValueFactory(new PropertyValueFactory<>("date"));

		// 2
		TableColumn col2 = new TableColumn<>("Farm ID");
		col2.setPrefWidth(150);
		col2.setCellValueFactory(new PropertyValueFactory<>("farmID"));

		// 3
		TableColumn col3 = new TableColumn<>("Total Weight");
		col3.setPrefWidth(150);
		col3.setCellValueFactory(new PropertyValueFactory<>("weight"));

		// 4
		TableColumn col4 = new TableColumn<>("Percent of Total Weight of All Farms (%)");
		col4.setPrefWidth(300);
		col4.setCellValueFactory(new PropertyValueFactory<>("percent"));

		// table.setItems(data);
		table.getColumns().addAll(col1, col2, col3, col4);
		table.setLayoutY(150);
		table.setPadding(new Insets(0, 20, 0, 20));

		// Group
		Group group = new Group();
		group.getChildren().addAll(vbox, confirm, bt_export, table);

		Scene mainScene = new Scene(group);

		// Handler
		DateMyHandler handler = new DateMyHandler(confirm, table, startPicker, endPicker);

		confirm.setOnAction(handler);
		primaryStage.setHeight(600);
		primaryStage.setWidth(840);
		primaryStage.setTitle(title);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	public class DateMyHandler implements javafx.event.EventHandler<ActionEvent> {
		private String A = "2020";
		private TableView table;
		private Button button;
		private DatePicker startPicker;
		private DatePicker endPicker;

		public DateMyHandler(Button confirm, TableView table, DatePicker startPicker, DatePicker endPicker) {
			this.button = confirm;
			this.table = table;
			this.startPicker = startPicker;
			this.endPicker = endPicker;
		}

		private ObservableList<Data> extract(TextField text1, TextField text2, MilkData map) {
			ObservableList<Data> oneMonthData = FXCollections.observableArrayList();
			TreeMap<String, TreeMap<String, TreeMap<String, Integer>>> allMonth = map.getMilkMap();
			return null;
		}

		@Override
		public void handle(ActionEvent arg0) {
			LocalDate startDate = startPicker.getValue();
			LocalDate endDate = endPicker.getValue();
			List<DataRangeItem> list = new ArrayList<>();
			int sum = milkData.getDataByTimeRange(CommonMilkTool.formatDate(startDate), CommonMilkTool.formatDate(endDate), list);
			table.getItems().clear();
			table.getItems().addAll(list);
		}

		public class Data {
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

			public Data(String Data, int FarmID, int Weight, double Percentage) {
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

  private String export2File(TableView<DataRangeItem> table) {
    String rpName = "DataRangeReport_" + CommonMilkTool.formatDate(LocalDate.now()) + ".txt";
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(new File(rpName), false);
      try {
        fos.write(DataRangeItem.getTitles().getBytes("UTF-8"));
        fos.write("\n".getBytes("UTF-8"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      for(DataRangeItem item : table.getItems()) {
        try {
          fos.write(item.getValueString().getBytes("UTF-8"));
          fos.write("\n".getBytes("UTF-8"));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }finally {
      if(fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return rpName;
  }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
