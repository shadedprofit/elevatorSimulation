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
    return passengersWaiting;
  }

  public void waitForElevator() {
    passengersWaiting++;
    building.elevator().summonElevator(floorNumber);
  }

  public void addPassenger() {
    passengersWaiting++;
  }

  public void removePassenger() {
    passengersWaiting--;
  }

}  
