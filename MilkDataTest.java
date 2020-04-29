/**
 * MilkDataTest.java created by wenxiyang on MacBook Pro in a2_1
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


import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * MilkDataTest - TODO Describe purpose of this user-defined type
 * @author Wenxi Yang (2020)
 *
 */
class MilkDataTest {

	@Test
	void test001_readSmall() {
		MilkData mData = new MilkData();
		try {
			mData.readMilkData("csv/small/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void test002_readMedium() {
		MilkData mData = new MilkData();
		try {
			mData.readMilkData("csv/medium/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void test003_readLarge() {
		MilkData mData = new MilkData();
		try {
			mData.readMilkData("csv/large/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void test004_readMissing() {
		MilkData mData = new MilkData();
		try {
			mData.readMilkData("csv/missing/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void test005_readError() {
		MilkData mData = new MilkData();
		try {
			mData.readMilkData("csv/error/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void test006_writeSmall() {
		MilkData mData = new MilkData();
		try {
			mData.readMilkData("csv/small/");
			mData.writeMilkData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
