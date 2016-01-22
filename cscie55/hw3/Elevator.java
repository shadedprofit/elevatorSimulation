package cscie55.hw3;

import java.util.*;

public class Elevator {

  private int currentFloor;
  private Set<Passenger> passengers = new HashSet<Passenger>();
  private int[] stops;
  private Building building;
  private String direction;


  public static final int CAPACITY = 10;

  public Elevator(Building building) {
    this.direction = "up";
    this.building = building;
    this.currentFloor = 1;
    this.stops = new int[7];
  }

  public void move() {
    System.out.println("Current floor: " + String.valueOf(currentFloor) + ", Passengers: " + String.valueOf(passengers.size()));
    if (currentFloor == 7) {
      direction = "down";
    }

    if (currentFloor == 1) {
      direction = "up";
    }

    if (direction.equals("up")) {
      currentFloor++;
    } else {
      currentFloor--;
    }

    if (this.hasStop(currentFloor)) {
      this.offBoardPassengers();
      Queue<Passenger> passengersWaiting = new LinkedList<Passenger>();
      if (direction.equals("up") || currentFloor == 1) {
        passengersWaiting = building.floor(currentFloor).getPassengerUpQueue();
      } else if (direction.equals("down")) {
        passengersWaiting = building.floor(currentFloor).getPassengerDownQueue();
      }

      int sizeofQueue = passengersWaiting.size();
      for (int i = 0; i < sizeofQueue; i++) {
          try {
            this.boardPassenger(passengersWaiting.element());
          } catch (ElevatorFullException e) {
            e.printStackTrace();
          }
      }
    }
  }

  public void boardPassenger(Passenger p) throws ElevatorFullException {
    if (passengers.size() < CAPACITY) {
      passengers.add(p);
      building.floor(currentFloor).removePassengerFromQueue(p);
      this.summonElevator(p.destinationFloor());
      System.out.println("Passenger boarded, total in elevator is " + String.valueOf(passengers.size()));
    } else {
       throw new ElevatorFullException("Elevator is full!");
    }

  }

  public void summonElevator(int floor) {
    stops[floor - 1] = 1;
  }

  public int currentFloor() {
    return currentFloor;
  }

  public Set<Passenger> passengers() {
    return passengers;
  }

  public boolean goingUp() {
    return direction.equals("up");
  }

  public boolean goingDown() {
    return direction.equals("down");
  }

  private void offBoardPassengers() {
    Set<Passenger> toRemove = new HashSet<Passenger>();
    for (Passenger p : passengers) {
      if (p.destinationFloor() == currentFloor) {
        p.arrive();
        toRemove.add(p);
      }
    }
    building.floor(currentFloor).makeResidents(toRemove);
    passengers.removeAll(toRemove);
    toRemove.clear();
    this.checkToRemoveStop();
  }

  private void checkToRemoveStop() {
    if (building.floor(currentFloor).getPassengerDownQueue().isEmpty() && building.floor(currentFloor).getPassengerUpQueue().isEmpty()) {
      stops[currentFloor - 1] = 0;
    }
  }

  private boolean hasStop(int floor) {
    return stops[floor - 1] == 1;
  }

  public String toString() {
    String passengerOutput;
    if (passengers.size() == 1) {
      passengerOutput = "1 passenger";
    } else {
      passengerOutput = String.valueOf(passengers.size()) + " passengers";
    }
    return "Floor " + currentFloor + ": " + passengerOutput;
  }
}