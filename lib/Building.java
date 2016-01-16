package cscie55.hw2;

public class Building {

  private Elevator elevator;
  private Floor[] floors = new Floor[7];
  private static final int numFloors = 7;

  public Building() {
    this.elevator = new Elevator(this);
    for (int i = 0; i < this.numFloors; i++) {
      this.floors[i] = new Floor(this, i + 1);
    }
  }

  public Elevator elevator() {
    return this.elevator;
  }

  public Floor floor(int floorNumber) {
    return this.floors[floorNumber - 1];
  }

}