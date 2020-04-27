

public class MilkItem {
  
  private String date;
  private String farmID;
  private Integer weight;
  
  public MilkItem(String date, String farmID, Integer weight) {
    super();
    this.date = date;
    this.farmID = farmID;
    this.weight = weight;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getFarmID() {
    return farmID;
  }

  public void setFarmID(String farmID) {
    this.farmID = farmID;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }
}
