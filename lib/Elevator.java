package cscie55.hw2;

import java.io.*;

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
    // before elevator moves, anyone gets off that needs to do so
    if ((this.passengers[this.currentFloor - 1][1] == 1)) {
      if (this.passengers[this.currentFloor - 1][0] > 0) {
        this.offBoardPassengers();
      }
      // if there are no longer any passengers, remove the flag for the elevator to stop on that floor
      if (this.passengers[this.currentFloor - 1][0] == 0) {
        this.passengers[this.currentFloor - 1][1] = 0;
      }
    }

    // if people are waiting to get on the elevator, they get on
    int passengersWaiting = this.building.floor(this.currentFloor).passengersWaiting();
    if (this.currentFloor >= 2) {
      for (int i = 0; i < passengersWaiting; i++) {
        // change later when every passenger is not bound for the 1st floor
        try {
          this.boardPassenger(1); // replace one with variable from passenger class
        } catch (cscie55.hw2.ElevatorFullException e) {
          e.printStackTrace();
        }
      }
    }
    // base case terminates the recursive call when there are no more stops
    if (!this.areStops(this.passengers)) {
      return;
    }
    System.out.println("Current floor: " + String.valueOf(this.currentFloor) + ", Passengers: " + String.valueOf(this.totalPassengers));

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

    this.move();
  }

  public void boardPassenger(int destinationFloorNumber) throws ElevatorFullException {
    if (this.totalPassengers < CAPACITY) {
      this.passengers[destinationFloorNumber - 1][0]++;
      this.totalPassengers++;
      this.building.floor(this.currentFloor).removePassenger();
      if (this.passengers[destinationFloorNumber - 1][1] == 0) {
        this.passengers[destinationFloorNumber - 1][1] = 1;
      }
      System.out.println("Passenger boarded, total in elevator is " + String.valueOf(this.totalPassengers));
      return;
    } else {
       throw new ElevatorFullException("Elevator is full!");
    }
  }

  public void summonElevator(int floor) {
    if (this.passengers[floor - 1][1] == 0) {
      this.passengers[floor - 1][1] = 1;
    }
    this.move();
  }

  public int currentFloor() {
    return this.currentFloor;
  }

  public int passengers() {
    return this.totalPassengers;
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

  // public String toString() {
  //   String passengerOutput;
  //   if (this.totalPassengers == 1) {
  //     passengerOutput = "1 passenger";
  //   } else {
  //     passengerOutput = String.valueOf(this.totalPassengers) + " passengers";
  //   }
  //   return "Floor " + this.currentFloor + ": " + passengerOutput;
  // }
}