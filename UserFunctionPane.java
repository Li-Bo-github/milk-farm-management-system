/**
 * UserFunctionPane.java created by wenxiyang on MacBook Pro in a2
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

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The Class UserFunctionPane.
 */
public class UserFunctionPane {

	/** The pane. */
	private static GridPane pane;

	/**
	 * Gets the user function pane.
	 *
	 * @return the user function pane
	 */
	public static GridPane getUserFunctionPane(Stage stage) {
		if (pane == null) {
			pane = new GridPane();
			pane.setPadding(new Insets(10, 10, 10, 60));
			// Setting the vertical and horizontal gaps between the columns
			pane.setVgap(20);
			pane.setHgap(10);
			Text t1 = new Text("User Functions");
			t1.setFont(Font.font(22));
			pane.add(t1, 1, 0);

			ToggleGroup radioGroup = new ToggleGroup();

			RadioButton rbtFarmRpt = new RadioButton("Farm Report");
			rbtFarmRpt.setFont(Font.font(18));
			rbtFarmRpt.setToggleGroup(radioGroup);
			pane.add(rbtFarmRpt, 1, 2);

			RadioButton rbtAnnualRpt = new RadioButton("Annual Report");
			rbtAnnualRpt.setFont(Font.font(18));
			rbtAnnualRpt.setToggleGroup(radioGroup);
			pane.add(rbtAnnualRpt, 1, 3);

			RadioButton rbtMonthRpt = new RadioButton("Monthly Report");
			rbtMonthRpt.setFont(Font.font(18));
			rbtMonthRpt.setToggleGroup(radioGroup);
			pane.add(rbtMonthRpt, 1, 4);

			RadioButton rbtDRRpt = new RadioButton("Date Range Report");
			rbtDRRpt.setFont(Font.font(18));
			rbtDRRpt.setToggleGroup(radioGroup);
			pane.add(rbtDRRpt, 1, 5);

			Button btReport = new Button("Confirm");
			btReport.setFont(Font.font(14));
			pane.add(btReport, 2, 6);
			btReport.setOnAction(new EventHandler<ActionEvent>() {

				/**
				 * Handle actions.
				 *
				 * @param event the event
				 */
				@Override
				public void handle(ActionEvent event) {
					RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
					if (selectedRadioButton.equals(rbtFarmRpt)) {
						System.out.println("call Farm Report");
						FarmReport farmR = new FarmReport();
						Stage d2Stage = new Stage();
						try {
							farmR.start(d2Stage);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (selectedRadioButton.equals(rbtAnnualRpt)) {
						AnnualReport annualR = new AnnualReport();
						Stage d2Stage = new Stage();
						annualR.start(d2Stage);
						System.out.println("call Annual Report");
					} else if (selectedRadioButton.equals(rbtMonthRpt)) {
						System.out.println("call Monthly Report");
					} else {
						System.out.println("call Date Range Report");
						DateRangeReport dateR = new DateRangeReport();
						Stage d2Stage = new Stage();
						try {
							dateR.start(d2Stage);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});

		}
		return pane;
	}

}
