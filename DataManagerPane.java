/**
 * DataManagerPane.java created by wenxiyang on MacBook Pro in a2
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
 * Device:		Wenxi’s MacBook Pro
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

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * The Class UserFunctionPane.
 */
public class DataManagerPane {

	/** The pane. */
	private static GridPane pane;
	
	/** The raw data pane. */
	private static RawDataPane rawDataPane;
	
	/** The raw data stage. */
	private static Stage rawDataStage;

	/**
	 * Gets the user function pane.
	 *
	 * @return the user function pane
	 */
	public static GridPane getDataManagerPane(MilkData milkData) {
		if (pane == null) {
			rawDataPane = RawDataPane.createRawDataPane(milkData);
			pane = new GridPane();
			pane.setPadding(new Insets(10, 10, 10, 20));
			// Setting the vertical and horizontal gaps between the columns
			pane.setVgap(20);
			pane.setHgap(20);

			Button bt_ManageData = new Button("Manage Data");
			bt_ManageData.setPrefWidth(120);
			pane.add(bt_ManageData, 2, 0);
			bt_ManageData.setOnAction(action -> {
				if (rawDataStage == null) {
					rawDataStage = new Stage();
					Scene scene = new Scene(rawDataPane.getMyPane());
					rawDataStage.setScene(scene);
				}
				rawDataStage.show();
			});

			ListView<File> listV = new ListView<File>();
			listV.setPrefWidth(600);
			pane.add(listV, 1, 1, 2, 1);

			DirectoryChooser dirChooser = new DirectoryChooser();
			dirChooser.setInitialDirectory(new File("csv"));
			Hyperlink hyperlink = new Hyperlink("Read Data Files...");
			hyperlink.setFont(Font.font(15));
			pane.add(hyperlink, 1, 0);
			Stage fileStage = new Stage();

			Comparator<File> comparator = (f1, f2) -> {
				String name = f1.getName();
				name = name.substring(0, name.length() - 4);
				String[] darr1 = name.split("-");
				name = f2.getName();
				name = name.substring(0, name.length() - 4);
				String[] darr2 = name.split("-");
				int res = -1;
				if (darr1.length > 1 && darr1.length == darr2.length) {
					try {
						for (int i = 0; i < darr1.length; i++) {
							res = Integer.compare(Integer.valueOf(darr1[i]), Integer.valueOf(darr2[i]));
							if (res != 0) {
								break;
							}
						}
					} catch (NumberFormatException e) {
						System.err.println("Error data, weight number format error: " + f1 + ", " + f2);
					}
				}
				return res;
			};

			hyperlink.setOnAction((final ActionEvent e) -> {
				File csvDir = dirChooser.showDialog(fileStage);
				if (csvDir != null) {
					try {
						List<File> fList = milkData.readMilkData(csvDir.getAbsolutePath());
						Collections.sort(fList, comparator);
						rawDataPane.fillMonthComboBox();
						listV.getItems().clear();
						listV.getItems().addAll(fList);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				try {
					EventLog.getInstance().log("Read files from " + csvDir.getAbsolutePath() + ".");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});

		}
		return pane;
	}

}
