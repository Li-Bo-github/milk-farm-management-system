/**
 * DataManagerPane.java created by wenxiyang on MacBook Pro in a2
 *
 * Author: Wenxi Yang(wyang235@wisc.edu) Date: 4/21/2020
 *
 * Course: CS400 Semester: Spring 2020 Lecture: 001
 * 
 * IDE: Eclipse IDE for Java Developers Version: 2019-12 (4.14.0) Build id: 20191212-1212
 *
 * Device: Wenxiâ€™s MacBook Pro OS: MacOS Mojave Version: 10.14.4 OS Build: 18E226
 * 
 * List Collaboratons:
 * 
 * Other Credits:
 *
 * Known bugs:
 */

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * The Class UserFunctionPane.
 */
public class DataManagerPane {

  /** The pane. */
  private static GridPane pane;

  /**
   * Gets the user function pane.
   *
   * @return the user function pane
   */
  public static GridPane getDataManagerPane() {
    if (pane == null) {
      pane = new GridPane();
      pane.setPadding(new Insets(10, 10, 10, 60));
      // Setting the vertical and horizontal gaps between the columns
      pane.setVgap(20);
      pane.setHgap(10);
      DirectoryChooser folderChooser = new DirectoryChooser();
      Hyperlink hyperlink = new Hyperlink("Choose Data Files...");
      hyperlink.setFont(Font.font(15));
      pane.add(hyperlink, 1, 0);
      Stage fileStage = new Stage();
      hyperlink.setOnAction((final ActionEvent e) -> {
        File file = folderChooser.showDialog(fileStage);
        String dir = file.getAbsolutePath();

        MilkData readData = new MilkData();
        try {
          readData.readMilkData(dir);
          //不太清楚milkdata
          
          
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      });

    }
    return pane;
  }

}
