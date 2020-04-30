/**
 * AnnualMilk created by Weihang Guo on MacBook in a3
 * 
 * Author: Weihang Guo(wguo63@wisc.edu)
 * Date:   @4.21
 * 
 * Course: CS400
 * Semester: Spring 2020
 * Lecture: 002
 * 
 * IDE: Eclipse IDE for Java Developers
 * Version: 2019-12(4.14.0)
 * Build id: 20191212-1212
 * 
 * Device: LisaG's MACBOOK
 * OS: macOS Mojave
 * Version: 10.14.4
 * OS Build: 1.8 GHz Intel Core i5
 * 
 * List Collaborators: None
 * 
 * Other Credits: None
 * 
 * Known Bugs: None
 */
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

	/**
     * The Class Annual Milk.
     */
    public class AnnualMilk {
 
        /** The farm ID. */
        private final SimpleStringProperty farmID;
        
        /** The weight. */
        private final SimpleIntegerProperty weight;
        
        /** The percentage. */
        private final SimpleDoubleProperty percentage;
 
		/**
		 * Instantiates.
		 *
		 * @param farmID the farm ID
		 * @param weight the weight
		 * @param percentage the percentage
		 */
		AnnualMilk(String farmID, Integer weight, Double percentage) {
            this.farmID = new SimpleStringProperty(farmID);
            this.weight = new SimpleIntegerProperty(weight);
            this.percentage = new SimpleDoubleProperty(percentage);
        }
 
        /**
         * Gets the farm ID.
         *
         * @return the farm ID
         */
        public String getFarmID() {
            return farmID.get();
        }
 
 
        /**
         * Gets the weight.
         *
         * @return the weight
         */
        public Integer getWeight() {
            return weight.get();
        }
 
        /**
         * Gets the percentage.
         *
         * @return the percentage
         */
        public Double getPercentage() {
            return percentage.get();
        }
 
    }
    