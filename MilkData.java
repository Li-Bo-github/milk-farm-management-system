import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * MilkData.java created by wenxiyang on MacBook Pro in a2
 *
 * Author:		Wenxi Yang(wyang235@wisc.edu)
 * Date: 		4/26/2020
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
 * MilkData - Save the milk data in a tree map, including the functions of
 * load/write from/to file.
 * 
 * @author Wenxi Yang (2020)
 *
 */
public class MilkData {

	// first key is month, second key is farmID, third key is date
	private TreeMap<String, TreeMap<String, TreeMap<String, Integer>>> milkMap = new TreeMap<>(new MilkComparator());

	private String csvDir;

	/**
	 * Gets the milkMap.
	 * 
	 * @return the milk map TreeMap<month(year-month), TreeMap<farmID,
	 *         TreeMap<date(year-month-day), milk weight>>>
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
	public List<File> readMilkData(String csvDir) throws IOException {
		milkMap.clear();
		this.csvDir = csvDir;
		ArrayList<File> flist = new ArrayList<File>();
		File file = new File(csvDir);
		File[] fileList = file.listFiles();
		for (File fileObj : fileList) {
			String fileName = fileObj.getName();
			if (fileName.toLowerCase().endsWith(".csv")) {
				String year_month = fileName.substring(0, fileName.length() - 4);
				readTextFile(year_month, fileObj);
				flist.add(fileObj);
			} else {
				System.err.println("Error file: " + fileObj.getName());
			}
		}
		return flist;
	}

	/**
	 * Read text file.
	 *
	 * @param month   the month
	 * @param csvFile the csv file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void readTextFile(String month, File csvFile) throws IOException {
		if (!milkMap.containsKey(month)) {
			milkMap.put(month, new TreeMap<String, TreeMap<String, Integer>>(new MilkComparator()));
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

	/**
	 * Process line.
	 *
	 * @param monthMap the month map
	 * @param line     the line
	 */
	private void processLine(TreeMap<String, TreeMap<String, Integer>> monthMap, String line) {
		String[] rs = line.split(",");
		if (rs.length != 3) {
			System.err.println("Error data. Skip it: " + line);
		} else {
			String date = rs[0].trim();
			String farmID = rs[1].trim();
			String weights = rs[2].trim();
			Integer weight = null;
			try {
				weight = Integer.valueOf(weights);
				if (CommonMilkTool.verifyDate(date) && farmID.matches("Farm \\d{1,}")) {
					if (!monthMap.containsKey(farmID)) {
						monthMap.put(farmID, new TreeMap<String, Integer>(new MilkComparator()));
					}
					monthMap.get(farmID).put(date, weight);
				} else {
					System.err.println("Error data, date format error: " + line);
				}
			} catch (NumberFormatException e) {
				System.err.println("Error data, weight number format error: " + line);
			}

		}
	}

	/**
	 * Write milk data.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeMilkData() throws IOException {
		for (String key : milkMap.keySet()) {
			writeMonthData(key);
		}
	}

	/**
	 * Write month data.
	 *
	 * @param year_month the year-month
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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

	// TreeMap<month(year-month), TreeMap<farmID, TreeMap<date(year-month-day), milk
	// weight>>>
	public List<MilkItem> getDataByDay(String day) {
		List<MilkItem> list = new ArrayList<>();
		String month = day.substring(0, day.lastIndexOf("-"));
		TreeMap<String, TreeMap<String, Integer>> monthMap = this.milkMap.get(month);
		if (monthMap == null) {
			return list;
		}
		// TreeMap<farmID, TreeMap<date(year-month-day), milk weight>>
		for (Map.Entry<String, TreeMap<String, Integer>> farmEntry : monthMap.entrySet()) {
			for (Map.Entry<String, Integer> dayEntry : farmEntry.getValue().entrySet()) {
				if (dayEntry.getKey().equals(day)) {
					list.add(new MilkItem(day, farmEntry.getKey(), dayEntry.getValue()));
				}
			}
		}
		return list;
	}

	// TreeMap<month(year-month), TreeMap<farmID, TreeMap<date(year-month-day), milk
	// weight>>>
	public void removeData(String day, String farmID) {
		List<MilkItem> list = new ArrayList<>();
		String month = day.substring(0, day.lastIndexOf("-"));
		TreeMap<String, TreeMap<String, Integer>> monthMap = this.milkMap.get(month);
		if (monthMap == null) {
			return;
		}
		TreeMap<String, Integer> farmMap = monthMap.get(farmID);
		if (farmMap == null) {
			return;
		}
		farmMap.remove(day);
	}

	public boolean checkData(String day, String farmID) {
		List<MilkItem> list = new ArrayList<>();
		String month = day.substring(0, day.lastIndexOf("-"));
		TreeMap<String, TreeMap<String, Integer>> monthMap = this.milkMap.get(month);
		if (monthMap == null) {
			return false;
		}
		TreeMap<String, Integer> farmMap = monthMap.get(farmID);
		if (farmMap == null) {
			return false;
		}
		return farmMap.containsKey(day);
	}

	public void changeData(String day, String farmID, int weight) {
		List<MilkItem> list = new ArrayList<>();
		String month = day.substring(0, day.lastIndexOf("-"));
		TreeMap<String, TreeMap<String, Integer>> monthMap = this.milkMap.get(month);
		if (monthMap == null) {
			return;
		}
		TreeMap<String, Integer> farmMap = monthMap.get(farmID);
		if (farmMap == null) {
			return;
		}
		farmMap.put(day, weight);
	}

	public void addData(String day, String farmID, int weight) {
		List<MilkItem> list = new ArrayList<>();
		String month = day.substring(0, day.lastIndexOf("-"));
		if (!milkMap.containsKey(month)) {
			milkMap.put(month, new TreeMap<String, TreeMap<String, Integer>>(new MilkComparator()));
		}
		TreeMap<String, TreeMap<String, Integer>> monthMap = this.milkMap.get(month);
		if (!monthMap.containsKey(farmID)) {
			monthMap.put(farmID, new TreeMap<String, Integer>(new MilkComparator()));
		}
		TreeMap<String, Integer> farmMap = monthMap.get(farmID);
		farmMap.put(day, weight);
	}

	public List<MilkItem> getDataByMonth(String month) {
		List<MilkItem> list = new ArrayList<>();
		TreeMap<String, TreeMap<String, Integer>> monthMap = this.milkMap.get(month);
		if (monthMap == null) {
			return list;
		}
		// TreeMap<farmID, TreeMap<date(year-month-day), milk weight>>
		for (Map.Entry<String, TreeMap<String, Integer>> farmEntry : monthMap.entrySet()) {
			for (Map.Entry<String, Integer> dayEntry : farmEntry.getValue().entrySet()) {
				list.add(new MilkItem(dayEntry.getKey(), farmEntry.getKey(), dayEntry.getValue()));
			}
		}
		Collections.sort(list);
		return list;
	}

	// TreeMap<month(year-month), TreeMap<farmID, TreeMap<date(year-month-day), milk
	// weight>>>
	public List<MilkItem> getAllData() {
		List<MilkItem> list = new ArrayList<>();
		for (Map.Entry<String, TreeMap<String, TreeMap<String, Integer>>> monthEntry : this.milkMap.entrySet()) {
			for (Map.Entry<String, TreeMap<String, Integer>> farmEntry : monthEntry.getValue().entrySet()) {
				for (Map.Entry<String, Integer> dayEntry : farmEntry.getValue().entrySet()) {
					list.add(new MilkItem(dayEntry.getKey(), farmEntry.getKey(), dayEntry.getValue()));
				}
			}
		}
		Collections.sort(list);
		return list;
	}

	public Set<String> getFarmIDList() {
		Set<String> list = new HashSet<>();
		for (Map.Entry<String, TreeMap<String, TreeMap<String, Integer>>> monthEntry : this.milkMap.entrySet()) {
			list.addAll(monthEntry.getValue().keySet());
		}
		return list;
	}

	// TreeMap<month(year-month), TreeMap<farmID, TreeMap<date(year-month-day), milk
	// weight>>>
	public Set<String> getMonths() {
		return this.milkMap.keySet();
	}

	/**
	 * Gets the data for data range report.
	 *
	 * @param startDate the start date
	 * @param endDate   the end date
	 * @return the total weight
	 */
	public int getDataByTimeRange(String startDate, String endDate, List<DataRangeItem> list) {
		MilkComparator milkComparator = new MilkComparator();
		int sum = 0;
		for (Map.Entry<String, TreeMap<String, TreeMap<String, Integer>>> monthEntry : this.milkMap.entrySet()) {
			for (Map.Entry<String, TreeMap<String, Integer>> farmEntry : monthEntry.getValue().entrySet()) {
				for (Map.Entry<String, Integer> dayEntry : farmEntry.getValue().entrySet()) {
					if (milkComparator.compare(startDate, dayEntry.getKey()) <= 0
							&& milkComparator.compare(endDate, dayEntry.getKey()) >= 0) {
						list.add(new DataRangeItem(dayEntry.getKey(), farmEntry.getKey(), dayEntry.getValue(), 0));
						sum += dayEntry.getValue();
					}
				}
			}
		}
		for (DataRangeItem item : list) {
			double percentage = ((item.getWeight() * 0.1) / sum);
			item.setPercent((int)(percentage * 1000));
		}
		Collections.sort(list);
		return sum;
	}
}
