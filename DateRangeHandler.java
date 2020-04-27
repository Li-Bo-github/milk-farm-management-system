
/** MyHandler.java created by junxuanzhang on MacBook Air in ateam
 *  
 *  Author: 	Junxuan Zhang(jzhang2329@wisc.edu)
 *  Date: 		4/21/2020
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

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 * MyHandler
 * 
 * @author zhang (2020)
 *
 */
public class DateRangeHandler implements javafx.event.EventHandler<ActionEvent> {

	/** The button. */
	private Button button;

	/** The root. */
	private BorderPane root;

	/** The text. */
	private TextField text;

	/** The sample year. */
	private String A = "2020";

	/** The data. */
	private final ObservableList<Data> data = FXCollections.observableArrayList(new Data(A, "001", "100", "20")

	);

	/**
	 * Instantiates a new date range handler.
	 *
	 * @param c    the c
	 * @param root the root
	 * @param text the text
	 */
	public DateRangeHandler(Button c, BorderPane root, TextField text) {
		button = c;
		this.root = root;
		this.text = text;
	}

	/**
	 * Handle actions.
	 *
	 * @param arg0 the arg 0
	 */
	@Override
	public void handle(ActionEvent arg0) {

		String gt = null;
		gt = text.getText().toString();

		// add table
		TableView table = new TableView<>();
		table.setEditable(true);

		// 1
		TableColumn col1 = new TableColumn<>("Date");
		col1.setPrefWidth(250);
		col1.setCellValueFactory(new PropertyValueFactory<>("date"));

		// 2
		TableColumn col2 = new TableColumn<>("Farm ID");
		col2.setPrefWidth(250);
		col2.setCellValueFactory(new PropertyValueFactory<>("farmID"));

		// 3
		TableColumn col3 = new TableColumn<>("Total Weight");
		col3.setPrefWidth(250);
		col3.setCellValueFactory(new PropertyValueFactory<>("weight"));

		// 4
		TableColumn col4 = new TableColumn<>("Percent of Total Weight of All Farms");
		col4.setPrefWidth(250);
		col4.setCellValueFactory(new PropertyValueFactory<>("percentage"));

		table.setItems(data);
		table.getColumns().addAll(col1, col2, col3, col4);

		// col1.setCellValueFactory(new PropertyValueFactory(gt));

//		 System.out.println(gt);

//	class Data{
//		private final SimpleStringProperty Date;
//		private final SimpleStringProperty ID;
//		private final SimpleStringProperty Weight;
//		private final SimpleStringProperty percentage;
//		
//		private Data(String Data, String ID, String Weight, String percentage) {
//			this
//		}
//		
//	}
//	

		root.setBottom(table);

	}

	/**
	 * The Class Data.
	 */
	public static class Data {
		
		/** The Date. */
		private final SimpleStringProperty Date;
		
		/** The Farm ID. */
		private final SimpleStringProperty FarmID;
		
		/** The Weight. */
		private final SimpleStringProperty Weight;
		
		/** The Percentage. */
		private final SimpleStringProperty Percentage;

		/**
		 * Instantiates new data.
		 *
		 * @param date the date
		 * @param farmID the farm ID
		 * @param weight the weight
		 * @param percentage the percentage
		 */
		private Data(String date, String farmID, String weight, String percentage) {
			this.Date = new SimpleStringProperty(date);
			this.FarmID = new SimpleStringProperty(farmID);
			this.Weight = new SimpleStringProperty(weight);
			this.Percentage = new SimpleStringProperty(percentage);

		}

		/**
		 * Gets the date.
		 *
		 * @return the date
		 */
		public String getDate() {
			return Date.get();
		}

		/**
		 * Sets the date.
		 *
		 * @param date the new date
		 */
		public void setDate(String date) {
			Date.set(date);
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
			Date.set(farmID);
		}

		/**
		 * Gets the weight.
		 *
		 * @return the weight
		 */
		public String getWeight() {
			return Weight.get();
		}

		/**
		 * Sets the weight.
		 *
		 * @param weight the new weight
		 */
		public void setWeight(String weight) {
			Date.set(weight);
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
			Date.set(percentage);
		}
	}

}
