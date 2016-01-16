import java.io.*;
import cscie55.hw2.*;


public class ElevatorTest {
  public static void main(String[] args) {
    cscie55.hw2.Building building1 = new cscie55.hw2.Building();
    cscie55.hw2.Elevator elevator = building1.elevator();
    System.out.println(building1.elevator().toString());


//    try {
//      elevator.boardPassenger(1);
//      elevator.boardPassenger(1);
//    } catch (ElevatorFullException e) {
//      System.out.println("Error: can't board passenger");
//    }

//    System.out.println("No Passengers is " + String.valueOf(elevator.passengers()));
//    System.out.println("Floor 1 is " + String.valueOf(building1.floor(1)) );
//
//    elevator.boardPassenger(5);
//    elevator.boardPassenger(7);
//    System.out.println("No Passengers is " + String.valueOf(elevator.passengers()));
//
//    elevator.move();
//    System.out.println("No Passengers is " + String.valueOf(elevator.passengers()));

//    elevator.boardPassenger(3);
//    elevator.boardPassenger(6);
//    elevator.boardPassenger(2);
//    elevator.move();
//    System.out.println("No Passengers is " + String.valueOf(elevator.passengers()));

    building1.floor(7).waitForElevator();
    System.out.println("");
    building1.floor(3).waitForElevator();


    for (int i = 0; i < 10; i++) {
      try {
        elevator.boardPassenger(4);
      } catch (cscie55.hw2.ElevatorFullException e) {
        e.printStackTrace();
      }
    }

    try {
      elevator.boardPassenger(5);
    }
    catch (cscie55.hw2.ElevatorFullException e) {
      e.printStackTrace();
    }
    // System.out.println(elevator.toString());
    // System.out.println(elevator.move());
    // System.out.println(elevator.summonElevator(1));
    
    // for (int i = 1; i < 13; i++) {
      // System.out.println(elevator.toString());
      // System.out.println(elevator.move());
      // if (i == 7) {
      //   elevator.boardPassenger(1);
      // }
    // }
    // System.out.println(elevator.toString());
  }
}