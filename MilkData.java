import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MilkData.java created by wenxiyang on MacBook Pro in a2
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
 * OS:			MacOS Mojave
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
 * MilkData - Save the milk data in a tree map, including the functions of
 * load/write from/to file.
 * 
 * @author Wenxi Yang (2020)
 *
 */
public class MilkData {

	// first key is month, second key is farmID, third key is date
	private TreeMap<String, TreeMap<String, TreeMap<String, Integer>>> milkMap = new TreeMap<>();
	private String csvDir;

	/**
	 * Gets the milkMap.
	 * 
	 * @return the milk map TreeMap<month(year-month), TreeMap<farmID,
	 *         				TreeMap<date(year-month-day), milk weight>>>
	 */
	public TreeMap<String, TreeMap<String, TreeMap<String, Integer>>> getMilkMap() {
		return milkMap;
	}

	/**
	 * Read milk data from csv file directory (folder). For example, if we read csv
	 * files from the folder "small" which is provided by the professor, we enter
	 * "csv/small/" as the directory name.
	 *
	 * @param csvDir the csv dir
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void readMilkData(String csvDir) throws IOException {
		this.csvDir = csvDir;
		File file = new File(csvDir);
		File[] fileList = file.listFiles();
		for (File fileObj : fileList) {
			String fileName = fileObj.getName();
			if (fileName.toLowerCase().endsWith(".csv")) {
				String year_month = fileName.substring(0, fileName.length() - 4);
				readTextFile(year_month, fileObj);
			} else {
				System.err.println("Error file: " + fileObj.getName());
			}
		}
	}

	private void readTextFile(String month, File csvFile) throws IOException {
		if (!milkMap.containsKey(month)) {
			milkMap.put(month, new TreeMap<String, TreeMap<String, Integer>>());
		}
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(csvFile);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line = br.readLine();
			if (line != null) {
				line = br.readLine();
			}

			while (line != null) {
				processLine(milkMap.get(month), line);
				line = br.readLine();
			}
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
	}

	private void processLine(TreeMap<String, TreeMap<String, Integer>> monthMap, String line) {
		String[] rs = line.split(",");
		if (rs.length != 3) {
			System.err.println("Error data. Skip it: " + line);
		} else {
			String date = rs[0].trim();
			String farmID = rs[1].trim();
			String weights = rs[2].trim();
			String datePattern = "\\d{4}-\\d{1,2}-\\d{1,2}";
			Integer weight = null;
			try {
				weight = Integer.valueOf(weights);
				if (date.matches(datePattern) && farmID.matches("Farm \\d{1,}")) {
					if (!monthMap.containsKey(farmID)) {
						monthMap.put(farmID, new TreeMap<String, Integer>());
					}
					monthMap.get(farmID).put(date, weight);
				} else {
					System.err.println("Error data: " + line);
				}
			} catch (NumberFormatException e) {
				System.err.println("Error data: " + line);
			}

		}
	}

	public void writeMilkData() throws IOException {
		for (String key : milkMap.keySet()) {
			writeMonthData(key);
		}
	}

	private void writeMonthData(String year_month) throws IOException {
		TreeMap<String, TreeMap<String, Integer>> monthMap = milkMap.get(year_month);
		String pathName = csvDir + "/" + year_month + ".csv";
		File file = new File(pathName);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			osw.write("date,farm_id,weight\n");

			// first key is date, second key is farmID
			ArrayList<String> list = new ArrayList<String>();
			for (Map.Entry<String, TreeMap<String, Integer>> farmEntry : monthMap.entrySet()) {
				String farmID = farmEntry.getKey();
				TreeMap<String, Integer> map = farmEntry.getValue();
				for (Map.Entry<String, Integer> entry : map.entrySet()) {
					String date = entry.getKey();
					Integer weight = entry.getValue();
					list.add(date + "," + farmID + "," + weight);
				}
			}
			Collections.sort(list);
			list.forEach((item) -> {
				try {
					osw.write(item + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			osw.flush();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}
}
