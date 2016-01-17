package cscie55.hw2;

public class Elevator {

  // for data encapsulation, most fields will be private

  // Here we will use a nested array to track passengers bound for a floor and 
  // whether the elevator needs to stop on the floor.
  // Index values will represent the floor, with the first item in the nested array
  // representing the number of passengers bound for the floor and the second item indicating
  // whether a stop is required or not.  A '0' will represent no stop and a '1' will
  // represent a stop.
  private int currentFloor;
  private int[][] passengers;
  private int totalPassengers;
  private Building building;
  private String direction;


  public static final int CAPACITY = 10;
  
  // the constructor
  public Elevator(Building building) {
    this.direction = "up";
    this.totalPassengers = 0;
    this.passengers = new int[7][2];
    this.building = building;
    this.currentFloor = 1;
  }

  public void move() {
    if (currentFloor == 7) {
      direction = "down";
    }

    if (currentFloor == 1) {
      direction = "up";
    }

    if (direction == "up") {
      currentFloor++;
    } else {
      currentFloor--;
    }

    // anyone gets off that needs to do so
    if ((passengers[currentFloor - 1][1] == 1)) {
      if (passengers[currentFloor - 1][0] > 0) {
        this.offBoardPassengers();
      }
      // if there are no longer any passengers, remove the flag for the elevator to stop on that floor
      if (passengers[currentFloor - 1][0] == 0) {
        passengers[currentFloor - 1][1] = 0;
      }
    }

    // if people are waiting to get on the elevator, they get on
    int passengersWaiting = building.floor(currentFloor).passengersWaiting();
    if (currentFloor >= 2) {
      for (int i = 0; i < passengersWaiting; i++) {
        // change later when every passenger is not bound for the 1st floor
        try {
          this.boardPassenger(1); // replace one with variable from passenger class
        } catch (ElevatorFullException e) {
          e.printStackTrace();
        }
      }
    }
    System.out.println("Current floor: " + String.valueOf(currentFloor) + ", Passengers: " + String.valueOf(totalPassengers));
  }

  public void boardPassenger(int destinationFloorNumber) throws ElevatorFullException {
    if (totalPassengers < CAPACITY) {
      passengers[destinationFloorNumber - 1][0]++;
      totalPassengers++;
      building.floor(this.currentFloor).removePassenger();
      if (passengers[destinationFloorNumber - 1][1] == 0) {
        passengers[destinationFloorNumber - 1][1] = 1;
      }
      System.out.println("Passenger boarded, total in elevator is " + String.valueOf(totalPassengers));
    } else {
       throw new ElevatorFullException("Elevator is full!");
    }
  }

  public void summonElevator(int floor) {
    if (passengers[floor - 1][1] == 0) {
      passengers[floor - 1][1] = 1;
    }
  }

  public int currentFloor() {
    return currentFloor;
  }

  public int passengers() {
    return totalPassengers;
  }

  private void offBoardPassengers() {
    int offBoardingPassengers = passengers[currentFloor - 1][0];
    totalPassengers -= offBoardingPassengers;
    passengers[currentFloor - 1][0] = 0;
    passengers[currentFloor - 1][1] = 0;
  }

  private boolean areStops(int[][] passengerList) {
    boolean result = false;
    for (int[] aPassengerList : passengerList) {
      if (aPassengerList[1] == 1) {
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