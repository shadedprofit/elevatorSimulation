package cscie55.hw1.elevator;

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
  private String direction;

  private static final int numFloors = 7;
  
  // the constructor
  public Elevator() {
    currentFloor = 1;
    direction = "up";
    totalPassengers = 0;
    passengers = new int[7][2];
  }

  public void move() {
    // first check to see if anyone needs to get off at current floor
    if (passengers[currentFloor - 1][1] == 1) {
      this.offBoardPassengers();
    }
    System.out.println(this.toString());
    // base case terminates the recursive call when there are no more stops
    if (!this.areStops(passengers)) {
      return;
    }

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

    this.move();
  }

  public void boardPassenger(int floor) {
    passengers[floor - 1][0]++;
    totalPassengers++;
    if (passengers[floor - 1][1] == 0) {
      passengers[floor - 1][1] = 1;
    }
  }

  public void summonElevator(int floor) {
    if (passengers[floor - 1][1] == 0) {
      passengers[floor - 1][1] = 1;
    }
    this.move();
  }

  private void offBoardPassengers() {
    int offBoardingPassengers = passengers[currentFloor - 1][0];
    totalPassengers -= offBoardingPassengers;
    passengers[currentFloor - 1][0] = 0;
    passengers[currentFloor - 1][1] = 0;
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
    if (totalPassengers == 1) {
      passengerOutput = "1 passenger";
    } else {
      passengerOutput = String.valueOf(totalPassengers) + " passengers";
    }
    return "Floor " + currentFloor + ": " + passengerOutput;
  }
}