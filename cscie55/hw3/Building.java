package cscie55.hw3;

public class Building {

  private Elevator elevator;
  private Floor[] floors = new Floor[7];

  public static final int FLOORS = 7;

  public Building() {
    elevator = new Elevator(this);
    for (int i = 0; i < FLOORS; i++) {
      floors[i] = new Floor(this, i + 1);
    }
  }

  public Elevator elevator() {
    return elevator;
  }

  public Floor floor(int floorNumber) {
    return floors[floorNumber - 1];
  }

  public void enter(Passenger passenger) {
    this.floor(1).enterGroundFloor(passenger);
  }

}