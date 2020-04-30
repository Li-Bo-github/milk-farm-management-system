/**
 * MonthlyReport created by Ni Pinzhi
 *
 * Author: Ni Pinzhi(pni4@wisc.edu)
 * Date:   @4.21
 *
 * Course: CS400
 * Semester: Spring 2020
 * Lecture: 002
 *
 * IDE: IntelliJ
 * Version: 2019
 *
 *
 * List Collaborators: None
 *
 * Other Credits: None
 *
 * Known Bugs: None
 */

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * The Class MonthlyReport.
 */

public class MonthlyReport extends Application {

	private static final java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * The private helper method for read from csv files
	 *
	 * @param year
	 * @param month
	 * @param map
	 * @return
	 */
	private ObservableList<Milk> extract(TextField year, TextField month, MilkData map) {
//		try {
//			int y = Integer.parseInt(year.getText()); // desired year
//		} catch (IllegalArgumentException e) {
//			Scene sc = new Scene(new Group());
//
//		}
		int y = Integer.parseInt(year.getText()); // desired year
		int mon = Integer.parseInt(month.getText()); // this is the desired month
		ObservableList<Milk> oneMonthData = FXCollections.observableArrayList();
		TreeMap<String, TreeMap<String, TreeMap<String, Integer>>> allMonth = map.getMilkMap();
		String tag = y + "-" + mon;
		TreeMap<String, TreeMap<String, Integer>> oneMonth = allMonth.get(tag);
		ArrayList<String> allID = new ArrayList<>();
		ArrayList<Integer> allWeight = new ArrayList<>();
		ArrayList<Double> percentage = new ArrayList<>();
		for (String ID : oneMonth.keySet()) {
			TreeMap<String, Integer> oneFarm = oneMonth.get(ID);
			int tot = 0;
			for (String date : oneFarm.keySet()) {
				tot += oneFarm.get(date);
			}
			//Milk tempMilk = new Milk((String) ID, tot, 0.0);
			allID.add(ID);
			allWeight.add(tot);
		}
		Integer whole = 0;
		for (Integer integer : allWeight) {
			whole += integer;
		}
		// System.out.println(whole);
		for (Integer integer : allWeight) {
			double temp = integer.doubleValue() / whole.doubleValue();
			percentage.add(Double.parseDouble(df.format(100 * temp)));
		}
		for (int l = 0; l < allID.size(); l++) {
			oneMonthData.add(new Milk(allID.get(l), allWeight.get(l), percentage.get(l)));
		}
		return oneMonthData;
	}

	/**
	 * Start.
	 *
	 * @param stage the stage
	 */
	@Override
	public void start(Stage stage) throws IOException {
		Scene scene = new Scene(new Group());
		stage.setTitle("Monthly Report");
		stage.setWidth(680);
		stage.setHeight(600);
		//create a label for title
		Label title = new Label("Monthly Report");
		title.setFont(new Font("Arial", 20));

		MilkData map = new MilkData();
		map.readMilkData("csv/small/");

		//create a table to show the monthly report
		TableView<Milk> table = new TableView<>();

		//build the farmID column
		TableColumn farmID = new TableColumn("FarmID");
		farmID.setMinWidth(150);
		farmID.setCellValueFactory(new PropertyValueFactory<>("farmID"));

		//build the weight column
		TableColumn weight = new TableColumn("Total Weight");
		weight.setMinWidth(150);
		weight.setCellValueFactory(new PropertyValueFactory<>("weight"));

		//build the percentage column
		TableColumn percentage = new TableColumn("Percent of Total Weight of All Farms");
		percentage.setMinWidth(300);
		percentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));
		table.getColumns().addAll(farmID, weight, percentage);

		//create a horizontal box
		HBox hbox1 = new HBox();
		hbox1.setSpacing(10);
		Label prompt0 = new Label(("Year:"));
		TextField year = new TextField();
		HBox hbox = new HBox();
		hbox.setSpacing(10);
		Label prompt = new Label("Month:");
		TextField month = new TextField();
		Button c = new Button("Confirm");
		Button ex = new Button("Export to file");

		// display the report according to selected month
		/*
		int y = Integer.parseInt(year.getText()); // desired year
		int mon = Integer.parseInt(month.getText()); // this is the desired month

		ObservableList<Milk> oneMonthData = FXCollections.observableArrayList();
		TreeMap allMonth = map.getMilkMap();
		String tag = y + "-" + mon;
		TreeMap oneMonth = (TreeMap) allMonth.get(tag);
		ArrayList<String> allID = new ArrayList<>();
		ArrayList<Integer> allWeight = new ArrayList<>();
		for (Object ID : oneMonth.keySet()) {
			TreeMap oneFarm = (TreeMap) oneMonth.get(ID);
			int tot = 0;
			for (Object date : oneFarm.keySet()) {
				tot += (int)oneFarm.get(date);
			}
			//Milk tempMilk = new Milk((String) ID, tot, 0.0);
			allID.add((String) ID);
			allWeight.add(tot);
		}
		int whole = 0;
		for (Integer integer : allWeight) {
			whole += integer;
		}
		for (int l = 0; l < allID.size(); l++) {
			oneMonthData.add(new Milk(allID.get(l), allWeight.get(l), (double) (allWeight.get(l) / whole)));
		}
		 */

		c.addEventHandler(ActionEvent.ACTION, (e) -> table.setItems(extract(year, month, map)));
		ex.addEventHandler(ActionEvent.ACTION, (e) -> {
			try {
				map.writeMilkData();
			} catch (IOException exc) {
				System.out.println("Error when export as file!");
			}
		});
		hbox.getChildren().addAll(prompt0, year, prompt, month, c, ex);
		//hbox1.getChildren().addAll(c, ex);

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
		RadioButton order2 = new RadioButton("Descending Order");
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

	/**
	 * The Class Milk.
	 */
	public static class Milk {

		/** The farm ID. */
		private final SimpleStringProperty farmID;

		/** The weight. */
		private final SimpleIntegerProperty weight;

		/** The percentage. */
		private final SimpleDoubleProperty percentage;

		/**
		 * Instantiates.
		 *
		 * @param farmID the farm ID
		 * @param weight the weight
		 * @param percentage the percentage
		 */
		private Milk(String farmID, Integer weight, Double percentage) {
			this.farmID = new SimpleStringProperty(farmID);
			this.weight = new SimpleIntegerProperty(weight);
			this.percentage = new SimpleDoubleProperty(percentage);
		}

		/**
		 * Gets the farm ID.
		 *
		 * @return the farm ID
		 */
		public String getFarmID() {
			return farmID.get();
		}


		/**
		 * Gets the weight.
		 *
		 * @return the weight
		 */
		public Integer getWeight() {
			return weight.get();
		}

		/**
		 * Gets the percentage.
		 *
		 * @return the percentage
		 */
		public Double getPercentage() {
			return percentage.get();
		}

	}

}
