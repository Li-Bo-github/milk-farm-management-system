/**
 * MilkItem.java created by wenxiyang on MacBook Pro in a2
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

/**
 * MilkItem - Provides get and set methdos for milk items
 * 
 * @author Wenxi Yang (2020)
 *
 */
public class MilkItem implements Comparable<MilkItem> {

	/** The date. */
	private String date;

	/** The farm ID. */
	private String farmID;

	/** The weight. */
	private int weight;

	/**
	 * Instantiates a new milk item.
	 *
	 * @param date   the date
	 * @param farmID the farm ID
	 * @param weight the weight
	 */
	public MilkItem(String date, String farmID, int weight) {
		super();
		this.date = date;
		this.farmID = farmID;
		this.weight = weight;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Gets the farm ID.
	 *
	 * @return the farm ID
	 */
	public String getFarmID() {
		return farmID;
	}

	/**
	 * Sets the farm ID.
	 *
	 * @param farmID the new farm ID
	 */
	public void setFarmID(String farmID) {
		this.farmID = farmID;
	}

	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Sets the weight.
	 *
	 * @param weight the new weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * Compare milk items.
	 *
	 * @param o the o
	 * @return the int
	 */
	@Override
	public int compareTo(MilkItem o) {
		MilkComparator milkCmp = new MilkComparator();
		int res = milkCmp.compare(this.date, o.getDate());
		if (res == 0) {
			res = milkCmp.compare(this.farmID, o.getFarmID());
		}
		return res;
	}

}
