
/**
 * RawDataPane.java created by wenxiyang on MacBook Pro in a2
 *
 * Author:		Wenxi Yang(wyang235@wisc.edu)
 * Date: 		4/21/2020
 *
 * Course: 		CS400
 * Semester: 	Spring 2020
 * Lecture: 	001
 * 
 * IDE:			Eclipse IDE for Java Developers
 * Version:		2019-12 (4.14.0)
 * Build id:	20191212-1212
 *
 * Device:		Wenxi's MacBook Pro
 * OS:			MacOS Mojave
 * Version:		10.14.4
 * OS Build: 	18E226
 * 
 * List Collaboratons: 
 * 
 * Other Credits: 
 *
 * Known bugs: 
 */

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

/**
 * The Class RawDataPane.
 */
public class RawDataPane {

	/** The pane. */
	private static RawDataPane pane;

	/** The table view. */
	private TableView<MilkItem> tableView;

	/** My pane. */
	private BorderPane myPane;

	/** The milk data. */
	private MilkData milkData;

	/** The month combo box. */
	private ComboBox<String> monthComboBox;

	/**
	 * Creates the raw data pane.
	 *
	 * @param milkData the milk data
	 * @return the raw data pane
	 */
	public static RawDataPane createRawDataPane(MilkData milkData) {
		if (pane == null) {
			Insets insets = new Insets(10);
			pane = new RawDataPane();
			pane.milkData = milkData;
			pane.createMilkRawTableV();
			Pane btPane = pane.createAddPane();
			Pane topPane = pane.createChoosePane();
			pane.myPane = new BorderPane();
			pane.myPane.setPadding(new Insets(10, 10, 10, 10));
			pane.myPane.setPrefWidth(840);
			pane.myPane.setPrefHeight(600);
			pane.myPane.setTop(topPane);
			pane.myPane.setCenter(pane.tableView);
			pane.myPane.setBottom(btPane);
			BorderPane.setMargin(topPane, insets);
			BorderPane.setMargin(pane.tableView, insets);
			BorderPane.setMargin(btPane, insets);
		}
		return pane;
	}

	/**
	 * Gets my pane.
	 *
	 * @return my pane
	 */
	public BorderPane getMyPane() {
		return myPane;
	}

	/**
	 * Creates the choose pane.
	 *
	 * @return the pane
	 */
	private Pane createChoosePane() {
		ToggleGroup radioGroup = new ToggleGroup();
		GridPane pane = new GridPane();
		// Insets top, right, bottom, left
		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.setVgap(8);
		pane.setHgap(8);
		RadioButton rbtAll = new RadioButton("All");
		rbtAll.setToggleGroup(radioGroup);
		pane.add(rbtAll, 0, 0);

		RadioButton rbtMonth = new RadioButton("Month");
		rbtMonth.setToggleGroup(radioGroup);
		pane.add(rbtMonth, 0, 1);
		monthComboBox = new ComboBox<>();
		monthComboBox.setPrefWidth(160);
		pane.add(monthComboBox, 1, 1);

		RadioButton rbtDay = new RadioButton("Day");
		rbtDay.setToggleGroup(radioGroup);
		pane.add(rbtDay, 0, 2);
		DatePicker datePicker = new DatePicker();
		pane.add(datePicker, 1, 2);
		rbtDay.setSelected(true);

		Button bt_search = new Button("Search");
		bt_search.setPrefWidth(80);
		pane.add(bt_search, 2, 2);

		bt_search.setOnAction(action -> {
			RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
			if (selectedRadioButton.equals(rbtAll)) {
				List<MilkItem> list = milkData.getAllData();
				addMilkItems(list);
			} else if (selectedRadioButton.equals(rbtMonth)) {
				List<MilkItem> list = milkData.getDataByMonth(monthComboBox.getValue());
				addMilkItems(list);
			} else {
				LocalDate day = datePicker.getValue();
				List<MilkItem> list = milkData.getDataByDay(CommonMilkTool.formatDate(day));
				addMilkItems(list);
			}
		});

		return pane;
	}

	/**
	 * Creates the add pane.
	 *
	 * @return the grid pane
	 */
	private GridPane createAddPane() {
		TextField date = new TextField();
		date.setPrefWidth(200);
		TextField farm = new TextField();
		farm.setPrefWidth(200);
		TextField weight = new TextField();
		weight.setPrefWidth(200);
		Button bt_add = new Button("Add");
		bt_add.setPrefWidth(100);
		bt_add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String dateStr = date.getText();
				if (!CommonMilkTool.verifyDate(dateStr)) {
					new Alert(Alert.AlertType.NONE, "Date format error", new ButtonType[] { ButtonType.CLOSE }).show();
					return;
				}
				String farmStr = farm.getText().trim();
				if (farmStr.isEmpty()) {
					new Alert(Alert.AlertType.NONE, "Farm ID error", new ButtonType[] { ButtonType.CLOSE }).show();
					return;
				}
				int weightInt = 0;
				try {
					weightInt = Integer.valueOf(weight.getText().trim());
				} catch (NumberFormatException e) {
					new Alert(Alert.AlertType.NONE, "weight error", new ButtonType[] { ButtonType.CLOSE }).show();
					return;
				}
				if (!milkData.checkData(dateStr, farmStr)) {
					milkData.addData(dateStr, farmStr, weightInt);
					tableView.getItems().add(new MilkItem(dateStr, farmStr, weightInt));
				} else {
					new Alert(Alert.AlertType.NONE, "Date and Farm ID exist", new ButtonType[] { ButtonType.CLOSE })
							.show();
					return;
				}
			}
		});
		Button bt_delete = new Button("Delete");
		bt_delete.setTooltip(new Tooltip("Delete the selected row"));
		bt_delete.setPrefWidth(100);
		bt_delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MilkItem item = tableView.getItems().remove(tableView.getSelectionModel().getSelectedIndex());
				milkData.removeData(item.getDate(), item.getFarmID());
			}
		});
		Button bt_save = new Button("Save");
		bt_save.setTooltip(new Tooltip("Save the data change to csv files"));
		bt_save.setPrefWidth(100);
		bt_save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					milkData.writeMilkData();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(8);
		gridPane.setHgap(8);
		gridPane.add(new Text("Date:"), 0, 0);
		gridPane.add(date, 0, 1);
		gridPane.add(new Text("Farm ID:"), 1, 0);
		gridPane.add(farm, 1, 1);
		gridPane.add(new Text("Weight:"), 2, 0);
		gridPane.add(weight, 2, 1);
		gridPane.add(bt_add, 3, 1);
		gridPane.add(bt_delete, 4, 1);
		gridPane.add(bt_save, 5, 1);
		return gridPane;
	}

	/**
	 * Creates the milk raw table View.
	 */
	private void createMilkRawTableV() {
		tableView = new TableView<MilkItem>();
		tableView.setEditable(true);
		tableView.setPrefWidth(500);
		tableView.setPrefWidth(400);
		TableColumn<MilkItem, String> colDate = new TableColumn<>("date(year-month-day)");
		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		colDate.setEditable(false);
		colDate.setSortable(true);
		colDate.setPrefWidth(200);
		colDate.setCellFactory(TextFieldTableCell.forTableColumn());
		colDate.setComparator(new MilkComparator());
		// MilkItem refers to table data type, String refers to the according instance
		// variable type of MilkItem
		TableColumn<MilkItem, String> colFarmID = new TableColumn<>("Farm ID");
		// "farmID" refers to the according instance variable name of MilkItem
		colFarmID.setCellValueFactory(new PropertyValueFactory<>("farmID"));
		colFarmID.setPrefWidth(200);
		colFarmID.setEditable(false);
		colFarmID.setCellFactory(TextFieldTableCell.forTableColumn());
		colFarmID.setComparator(new MilkComparator());
		TableColumn<MilkItem, Integer> colWeight = new TableColumn<>("Weight");
		colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
		colWeight.setPrefWidth(200);
		colWeight.setEditable(true);
		colWeight.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		colWeight.setSortable(false);
		colWeight.setOnEditCommit((TableColumn.CellEditEvent<MilkItem, Integer> t) -> {
			MilkItem item = t.getTableView().getItems().get(t.getTablePosition().getRow());
			item.setWeight(t.getNewValue());
			milkData.changeData(item.getDate(), item.getFarmID(), item.getWeight());
		});
		tableView.getColumns().add(colDate);
		tableView.getColumns().add(colFarmID);
		tableView.getColumns().add(colWeight);
	}

	/**
	 * Fill in the month combo box.
	 */
	public void fillMonthComboBox() {
		monthComboBox.getItems().clear();
		monthComboBox.getItems().addAll(milkData.getMonths());
		// Select the first month item
		monthComboBox.getSelectionModel().selectFirst();
	}

	/**
	 * Adds the milk items.
	 *
	 * @param data the data
	 */
	private void addMilkItems(List<MilkItem> data) {
		ObservableList<MilkItem> list = tableView.getItems();
		list.clear();
		data.forEach((item) -> list.add(item));
	}

}
