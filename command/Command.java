package command;

import java.util.Scanner;

import parking.ParkingSpace;
import vehicle.Vehicle;



public class Command {
    private int floorNumber;
    Scanner scanner = new Scanner(System.in);
    ParkingSpace parkingSpace = new ParkingSpace();
   
    


    public void addFloor(){
        System.out.println("Floor Level");
        floorNumber = scanner.nextInt();
        parkingSpace.parkingLevel(floorNumber);
        parkingSpace.setUpEachLevel(floorNumber);
       // addSlots();

    }
    //public void addSlots(){
        
      //  parkingSpace.addSlotsInEachLevel(floorNumber);

    //}
    public void addVehicle(){
        System.out.println("Vehicle Number and Type");
        String licenseNumber,type;
        licenseNumber = scanner.next();
        type = scanner.next();
        Vehicle vehicle = new Vehicle(licenseNumber, type);
        int[] slotNumber = (int[]) parkingSpace.addVehicleToParkingSpace(vehicle);
        System.out.println(slotNumber[0] + " " + slotNumber[1]);
        

    }
    public void leaveVehicle(){
        //int[] slot= new int[2];
        System.out.println("Vehicle Number and type");
        String licenseNumber,type;
        licenseNumber = scanner.next();
        type=scanner.next();
        int[] slot = parkingSpace.leaveVehicle(licenseNumber,type);
        System.out.println("Vehicle Number "+ licenseNumber + "leave from slot "+slot[0] +" "+ slot[1]);

    }
    public void status(){
        parkingSpace.statusOfSPace();

    }
}
