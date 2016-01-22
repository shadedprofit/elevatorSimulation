package cscie55.hw3;

public class Passenger {

    private int id;
    private int currentFloor;
    private int destinationFloor;
    private String direction;

    public static final int UNDEFINED_FLOOR = -1;

    public Passenger(int passengerID) {
        id = passengerID;
        currentFloor = 1;
        destinationFloor = -1;
        direction = "undefined";
    }

    public int currentFloor() {
        return currentFloor;
    }

    public int destinationFloor() {
        return destinationFloor;
    }

    public void waitForElevator(int newDestinationFloor) {
        destinationFloor = newDestinationFloor;
        if (destinationFloor > currentFloor) {
            direction = "up";
        } else {
            direction = "down";
        }
    }

    public String direction() {
        return direction;
    }

    public void boardElevator() {
        currentFloor = UNDEFINED_FLOOR;
    }

    public void arrive() {
        currentFloor = destinationFloor;
        destinationFloor = UNDEFINED_FLOOR;
    }

    public String toString() {
        return "Current floor: " + String.valueOf(currentFloor)
                + " , Destination: " +  destinationFloor;
    }
}