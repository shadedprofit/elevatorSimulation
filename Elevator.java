package cscie55.hw1.elevator;

public class Elevator {

  // for data encapsulation, most fields will be private
  private int currentFloor;

  // Here we will use a nested array to track passengers bound for a floor and 
  // whether the elevator needs to stop on the floor.
  // Index values will represent the floor, with the first item in the nested array
  // representing the number of passengers bound for the floor and the second item indicating
  // whether a stop is required or not.  A '0' will represent no stop and a '1' will
  // represent a stop.
  private int[][] passengers;
  private int totalPassengers;
  private String direction;

  public static final int numFloors = 7;
  
  // the constructor
  public Elevator() {
    this.currentFloor = 1;
    this.direction = "up";
    this.totalPassengers = 0;
    this.passengers = new int[7][2];
  }

  public String move() {
    // base case terminates the recursive call when there are no more stops
    if (!this.areStops(this.passengers)) {
      return this.toString();
    }

    if (this.currentFloor == 7) {
      this.direction = "down";
    }

    if (this.currentFloor == 1) {
      this.direction = "up";
    }

  
    if (this.direction == "up") {
      this.currentFloor++;
    } else {
      this.currentFloor--;
    }

    if (this.passengers[this.currentFloor - 1][1] == 1) {
      this.offBoardPassengers();
    } 

    return this.toString() + " \n" + this.move();
  }

  public void boardPassenger(int floor) {
    this.passengers[floor - 1][0]++;
    this.totalPassengers++;
    if (this.passengers[floor - 1][1] == 0) {
      this.passengers[floor - 1][1] = 1;
    }
    return;
  }

  private int offBoardPassengers() {
    int offBoardingPassengers = this.passengers[this.currentFloor - 1][0];
    this.totalPassengers -= offBoardingPassengers;
    this.passengers[this.currentFloor - 1][0] = 0;
    this.passengers[this.currentFloor - 1][1] = 0;
    return offBoardingPassengers;
  }

  private boolean areStops(int[][] passengerList) {
    boolean result = false;
    for (int i = 0; i < passengerList.length; i++) {
      if (passengerList[i][1] == 1) {
        result = true;
        break;
      }
    }
    return result;
  }

  public String toString() {
    String passengerOutput;
    if (this.totalPassengers == 1) {
      passengerOutput = "1 passenger";
    } else {
      passengerOutput = String.valueOf(this.totalPassengers) + " passengers";
    }
    return "Floor " + this.currentFloor + ": " + passengerOutput;
  }
}