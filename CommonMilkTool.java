/**
 * CommonMilkTool.java created by wenxiyang on MacBook Pro in a2
 *
 * Author:		Wenxi Yang(wyang235@wisc.edu)
 * Date: 		4/29/2020
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Class CommonMilkTool.
 */
public class CommonMilkTool {

	/**
	 * Verify date.
	 *
	 * @param date the date
	 * @return true, if successful
	 */
	public static boolean verifyDate(String date) {
		String datePattern = "\\d{4}-\\d{1,2}-\\d{1,2}";
		if (!date.matches(datePattern)) {
			return false;
		}
		String[] darr = date.split("-");
		for (String str : darr) {
			if (str.startsWith("0")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Format date into "yyyy-M-d".
	 *
	 * @param date the date
	 * @return the string
	 */
	public static String formatDate(LocalDate date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d");
		return dtf.format(date);
	}
}
