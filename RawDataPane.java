
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class RawDataPane {

	private static RawDataPane pane;
	private TableView<MilkItem> tableView;
	private BorderPane myPane;
	private MilkData milkData;
	private ComboBox<String> monthComboBox;

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
			pane.myPane.setPrefWidth(800);
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

	public BorderPane getMyPane() {
		return myPane;
	}

	private Pane createChoosePane() {
		ToggleGroup radioGroup = new ToggleGroup();
		GridPane pane = new GridPane();
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
				System.out.println(day);
				List<MilkItem> list = milkData.getDataByDay(formatDate(day));
				addMilkItems(list);
			}
		});

		return pane;
	}
	
	private String formatDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
		return formatter.format(date);
	}

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

			}
		});
		Button bt_save = new Button("Save");
		bt_save.setPrefWidth(100);
		bt_save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

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
		gridPane.add(bt_save, 4, 1);
		return gridPane;
	}

	private void createMilkRawTableV() {
		tableView = new TableView<MilkItem>();
		tableView.setEditable(true);
		tableView.setPrefWidth(500);
		tableView.setPrefWidth(400);
		TableColumn<MilkItem, String> colDate = new TableColumn<>("date(year-month-day)");
		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		colDate.setEditable(true);
		colDate.setSortable(true);
		colDate.setPrefWidth(200);
		colDate.setCellFactory(TextFieldTableCell.forTableColumn());
		colDate.setOnEditCommit((TableColumn.CellEditEvent<MilkItem, String> t) -> {
			MilkItem item = t.getTableView().getItems().get(t.getTablePosition().getRow());
			item.setDate(t.getNewValue());
		});
		TableColumn<MilkItem, String> colFarmID = new TableColumn<>("Farm ID");
		colFarmID.setCellValueFactory(new PropertyValueFactory<>("farmID"));
		colFarmID.setPrefWidth(200);
		colFarmID.setEditable(true);
		colFarmID.setCellFactory(TextFieldTableCell.forTableColumn());
		colFarmID.setOnEditCommit((TableColumn.CellEditEvent<MilkItem, String> t) -> {
			MilkItem item = t.getTableView().getItems().get(t.getTablePosition().getRow());
			item.setFarmID(t.getNewValue());
		});
		TableColumn<MilkItem, Integer> colWeight = new TableColumn<>("Weight");
		colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
		colWeight.setPrefWidth(200);
		colWeight.setEditable(true);
		colWeight.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		colWeight.setOnEditCommit((TableColumn.CellEditEvent<MilkItem, Integer> t) -> {
			MilkItem item = t.getTableView().getItems().get(t.getTablePosition().getRow());
			item.setWeight(t.getNewValue());
		});
		tableView.getColumns().add(colDate);
		tableView.getColumns().add(colFarmID);
		tableView.getColumns().add(colWeight);
	}

	public void fillMonthComboBox() {
		monthComboBox.getItems().clear();
		monthComboBox.getItems().addAll(milkData.getMonths());
	}

	private void addMilkItems(List<MilkItem> data) {
		ObservableList<MilkItem> list = tableView.getItems();
		list.clear();
		data.forEach((item) -> list.add(item));
	}

}
