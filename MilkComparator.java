
/**
 * MilkComparator.java created by wenxiyang on MacBook Pro in a2_1
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

import java.util.Comparator;

/**
 * The Class MilkComparator helps to sort farmID and month.
 */
public class MilkComparator implements Comparator<String> {

	/**
	 * Compare.
	 *
	 * @param str1 the first String
	 * @param str2 the second String
	 * @return the int
	 */
	@Override
	public int compare(String str1, String str2) {
		String[] darr1 = str1.split("-");
		String[] darr2 = str2.split("-");
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
				System.err.println("Error data, weight number format error: " + str1 + ", " + str2);
			}
		} else {
			darr1 = str1.split(" ");
			darr2 = str2.split(" ");
			if (darr1.length == 2 && darr1.length == darr2.length) {
				res = darr1[0].compareTo(darr2[0]);
				if (res == 0) {
					res = Integer.compare(Integer.valueOf(darr1[1]), Integer.valueOf(darr2[1]));
				}
			}
		}
		return res;
	}

}
