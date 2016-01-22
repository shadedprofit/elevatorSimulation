package cscie55.hw3;

import java.util.*;

public class Floor {

  private Building building;
  private int floorNumber;
  private Queue<Passenger> passengerUpQueue = new LinkedList<Passenger>();
  private Queue<Passenger> passengerDownQueue = new LinkedList<Passenger>();
  private ArrayList<Passenger> residents = new ArrayList<Passenger>();

  public Floor(Building building, int floorNumber) {
    this.building = building;
    this.floorNumber = floorNumber;
  }


  public void waitForElevator(Passenger passenger, int destinationFloor) {
    if (residents.contains(passenger)) {
      residents.remove(passenger);
    }
    passenger.waitForElevator(destinationFloor);
    if (passenger.direction() == "up") {
      passengerUpQueue.add(passenger);
    } else if (passenger.direction() == "down"){
      passengerDownQueue.add(passenger);
    }
    building.elevator().summonElevator(floorNumber);
  }

  public boolean isResident(Passenger passenger) {
    return residents.contains(passenger);
  }

  public void makeResidents(Set<Passenger> offBoarders) {
    residents.addAll(offBoarders);
  }

  public void enterGroundFloor(Passenger passenger) {
    passenger.arrive();
    residents.add(passenger);
  }

  public void removePassengerFromQueue(Passenger passenger) {
    if (passengerUpQueue.contains(passenger)) {
      passengerUpQueue.remove(passenger);
    } else if (passengerDownQueue.contains(passenger)) {
      passengerDownQueue.remove(passenger);
    }
  }

  public Queue<Passenger> getPassengerUpQueue() {
    return passengerUpQueue;
  }

  public Queue<Passenger> getPassengerDownQueue() {
    return passengerDownQueue;
  }

}  
