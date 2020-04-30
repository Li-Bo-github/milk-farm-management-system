import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;


/**
 * EventLog.java created by wenxiyang on MacBook Pro in a2_1
 *
 * Author:		Wenxi Yang(wyang235@wisc.edu)
 * Date: 		@date
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
 * OS:				MacOS Mojave
 * Version:		10.14.4
 * OS Build: 	18E226
 * 
 * List Collaboratons: Name, email@wisc.edu, lecture number
 * 
 * Other Credits: describe other sources(web sites or people)
 *
 * Known bugs: describe known unsolved bugs here
 */

/**
 * The Class EventLog.
 * 
 * @author Wenxi Yang (2020)
 *
 */
public class EventLog {

	/** The instance. */
	private static EventLog instance;

	/** The fos. */
	private static FileOutputStream fos;

	/**
	 * Instantiates a new event log which cannot be accessed outside this class.
	 */
	private EventLog() {

	}

	/**
	 * Instantiates a EventLog object.
	 *
	 * @return single instance of EventLog
	 */
	public static EventLog getInstance() {
		if (instance == null) {
			instance = new EventLog();
			String logName = CommonMilkTool.formatDate(LocalDate.now()) + "_eventLog.log";
			File file = new File(logName);
			// append the log
			try {
				fos = new FileOutputStream(file, true);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		return instance;
	}

	/**
	 * Write log.
	 *
	 * @param logContent the log content
	 */
	public void log(String logContent) {
		try {
			fos.write((new Date()).toString().getBytes("UTF-8"));
			fos.write(", ".getBytes("UTF-8"));
			fos.write(logContent.getBytes("UTF-8"));
			fos.write("\n".getBytes("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Close the fos.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void close() throws IOException {
		fos.close();
	}
}
