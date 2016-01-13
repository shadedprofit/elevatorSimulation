import java.io.*;
import cscie55.hw1.elevator.*;

class ElevatorTest {
  public static void main(String[] args) {
    cscie55.hw1.elevator.Elevator elevator1 = new cscie55.hw1.elevator.Elevator();
    elevator1.boardPassenger(3);
    elevator1.boardPassenger(3);
    elevator1.boardPassenger(5);
    // elevator1.boardPassenger(7);

    System.out.println(elevator1.toString());
    System.out.println(elevator1.move());
    System.out.println(elevator1.summonElevator(1));
    
    // for (int i = 1; i < 13; i++) {
      // System.out.println(elevator1.toString());
      // System.out.println(elevator1.move());
      // if (i == 7) {
      //   elevator1.boardPassenger(1);
      // }
    // }
    // System.out.println(elevator1.toString());
  }
}