
/** Main.java created by junxuanzhang on MacBook Air in ateam
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

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
	private static final String title = "Milk Manager";
	private static final String DRR = "Date Range Report";
	private static final String Start = "Start Date (Year-Month-Day)";
	private static final String End = "End Date (Year-Month-Day)";
	private static final String AOrder = "Ascending Order";
	private static final String DOrder = "Decsending Order";
	private static final String confirm = "Confirm";
//	private static final String space1 = "                                                                              ";

	/**
	 * Start.
	 *
	 * @param primaryStage the primary stage
	 * @throws Exception the exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane root = new BorderPane();
		VBox vbox = new VBox();
		HBox hbox1 = new HBox();
		HBox hbox2 = new HBox();
		HBox hbox3 = new HBox();

		HBox hbox4 = new HBox();

		VBox vbox4 = new VBox();

		TextField text = new TextField();
		text.setPromptText("2020");
		TextField text2 = new TextField();
		text2.setPromptText("2020");

		CheckBox checkBox1 = new CheckBox();
		CheckBox checkBox2 = new CheckBox();

		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		// text.setPromptText("asdf");

		// top
		Label report = new Label(DRR);
		report.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		// report.setPrefWidth(300);

		root.setTop(report);

		// Center
		Label start = new Label(Start);
		hbox1.getChildren().addAll(start, text);

		String gt = null;
//		gt = text.getText();
		text.getText();
		// Label tg = new Label(gt);

		Label end = new Label(End);
		hbox2.getChildren().addAll(end, text2);

		Label AO = new Label(AOrder);
		Label DO = new Label(DOrder);
		Label space = new Label("       ");
		Label space1 = new Label("       ");
		Label space2 = new Label("       ");
		Label space3 = new Label("       ");
		Label space4 = new Label("       ");
		Label space5 = new Label("       ");
		Label space6 = new Label("       ");

		// hbox3.getChildren().addAll(AO,checkBox1,space,DO,checkBox2);
		vbox.getChildren().addAll(space, hbox1, space1, space2, space4, hbox2, space3, space5);

		Label space10 = new Label("                                                                          ");

		// Right
		Button c = new Button("Confrim");
		// hbox4.getChildren().addAll(c,space);
		// c.setPadding(new Insets(20));
		hbox4.getChildren().addAll(c, space10);
//        vbox4.setAlignment(Pos.CENTER);
		root.setRight(hbox4);

		// Handler
		DateRangeHandler handler = new DateRangeHandler(c, root, text);

		// register event handler for button
		c.setOnAction(handler);

		root.setCenter(vbox);

//		Label report1 = new Label(Start);
//		root.setCenter(report1);

		primaryStage.setTitle(title);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
