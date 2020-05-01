/**
 * DataRangeItem.java created by wenxiyang on MacBook Pro in a2
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
 * DataRangeItem - TODO Describe purpose of this user-defined type
 * @author Wenxi Yang (2020)
 *
 */
public class DataRangeItem extends MilkItem{
	
	/** The percent. */
	private int percent;

	/**
	 * @param date
	 * @param farmID
	 * @param weight
	 */
	public DataRangeItem(String date, String farmID, int weight, int percent) {
		super(date, farmID, weight);
		this.percent = percent;
	}

	/**
	 * Gets the percent.
	 *
	 * @return the percent
	 */
	public int getPercent() {
		return percent;
	}

	/**
	 * Sets the percent.
	 *
	 * @param percent the new percent
	 */
	public void setPercent(int percent) {
		this.percent = percent;
	}
	
  public static String getTitles() {
    return "farmID, weight, percentage";
  }
  
  public String getValueString() {
    return this.getFarmID() + "," + this.getWeight() + "," + percent;
  }
}
