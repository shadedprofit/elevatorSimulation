package cscie55.hw2;

public class Floor {

  private Building building;
  private int floorNumber;
  private int passengersWaiting;

  public Floor(Building building, int floorNumber) {
    this.building = building;
    this.floorNumber = floorNumber;
  }

  public int passengersWaiting() {
    return this.passengersWaiting;
  }

  public void waitForElevator() {
    this.passengersWaiting++;
    this.building.elevator().summonElevator(this.floorNumber);
  }

  public void addPassenger() {
    this.passengersWaiting++;
  }

  public void removePassenger() {
    this.passengersWaiting--;
  }

}  
