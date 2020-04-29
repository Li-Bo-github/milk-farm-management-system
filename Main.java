
/**
 * Main.java created by wenxiyang on MacBook Pro in a2
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.RadialGradient;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * The Class Main.
 */
public class Main extends Application {

	/** The milk data. */
	public static MilkData milkData = new MilkData();

	/**
	 * Start the program.
	 *
	 * @param primaryStage the primary stage
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			EventLog.getInstance().log("Start Milk Manager.");
			primaryStage.setTitle("Milk Manager");
			BorderPane root = createMainPane(primaryStage);
			Scene scene = new Scene(root, 840, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * This method is called when the application should stop, and provides a
     * convenient place to prepare for application exit and destroy resources.
     *
     * @throws java.lang.Exception if something goes wrong
     */
    public void stop() throws Exception {
    	EventLog.getInstance().log("Exit Milk Manager.");
    	EventLog.getInstance().close();
    }

	/**
	 * Creates the main pane.
	 *
	 * @return the border pane
	 * @throws FileNotFoundException when the file is not found
	 */
	private BorderPane createMainPane(Stage primaryStage) throws FileNotFoundException {
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(20, 20, 20, 20));
		FileInputStream input = new FileInputStream("cow1.png");
		Image imageL = new Image(input);
		ImageView ingvL = new ImageView(imageL);
		Text tt = new Text("Milk Manager");
		tt.setFont(Font.font("Kalam", FontWeight.BOLD, FontPosture.REGULAR, 70));
		tt.setFill(RadialGradient.valueOf("center 100px 100px, radius 200px, red  0%, #00b3ff 100%, black 30%"));
		HBox topPane = new HBox(ingvL, tt);
		HBox.setHgrow(tt, Priority.ALWAYS);
		HBox.setHgrow(ingvL, Priority.NEVER);
		topPane.setSpacing(80);
		topPane.setMaxHeight(180);
		topPane.setPrefHeight(180);
		root.setTop(topPane);
		Button bt_dataManager = new Button("Data Manager");
		bt_dataManager.setId("blue_button");
		bt_dataManager.setPrefWidth(200);
		Insets insets = new Insets(15);

		bt_dataManager.setOnAction(new EventHandler<ActionEvent>() {

			/**
			 * Handle actions.
			 *
			 * @param event the event
			 */
			@Override
			public void handle(ActionEvent event) {
				Pane centerPane = DataManagerPane.getDataManagerPane(milkData);
				root.setCenter(centerPane);
				BorderPane.setMargin(centerPane, insets);
			}
		});

		Button bt_userFun = new Button("User Functions");
		bt_userFun.setPrefWidth(200);
		bt_userFun.setId("blue_button");
		bt_userFun.setOnAction(new EventHandler<ActionEvent>() {

			/**
			 * Handle actions.
			 *
			 * @param event the event
			 */
			@Override
			public void handle(ActionEvent event) {
				Pane centerPane = UserFunctionPane.getUserFunctionPane(primaryStage);
				root.setCenter(centerPane);
				BorderPane.setMargin(centerPane, insets);
			}
		});

		Button bt_desc = new Button("Description");
		bt_desc.setPrefWidth(200);
		bt_desc.setId("blue_button");
		bt_desc.setOnAction(new EventHandler<ActionEvent>() {

			/**
			 * Handle actions.
			 *
			 * @param event the event
			 */
			@Override
			public void handle(ActionEvent event) {
				TextArea text = null;
				try {
					text = readTxt("Description.txt");
				} catch (IOException e) {
					e.printStackTrace();
				}
				root.setCenter(text);
				BorderPane.setMargin(text, insets);
			}
		});

		VBox leftPane = new VBox(bt_dataManager, bt_userFun, bt_desc);
		leftPane.setMaxWidth(200);
		leftPane.setPrefWidth(200);
		leftPane.setSpacing(20);
		root.setLeft(leftPane);
		BorderPane.setMargin(topPane, insets);
		BorderPane.setMargin(leftPane, insets);
		return root;
	}

	/**
	 * Read text.
	 *
	 * @param filename the filename
	 * @return the text area
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private TextArea readTxt(String filename) throws IOException {
		FileReader reader = new FileReader(filename);
		char[] chars = new char[1024];
		reader.read(chars);
		String text = new String(chars);
		TextArea textArea = new TextArea(text);
		textArea.setWrapText(true);
		return textArea;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
