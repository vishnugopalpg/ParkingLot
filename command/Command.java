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
        int[] slotNumber = new int[3];
        System.out.println("Vehicle Number and Type");
        String licenseNumber,type;
        licenseNumber = scanner.next();
        type = scanner.next();
        Vehicle vehicle = new Vehicle(licenseNumber, type);
        slotNumber =  parkingSpace.addVehicleToParkingSpace(vehicle);
        if(slotNumber[0]!=-1){
            System.out.println(slotNumber[0] + " " + slotNumber[1]);
        }else{
            System.out.println("No Slot availble");
        }

    }
    public void leaveVehicle(){
        int[] slot= new int[3];
        System.out.println("Vehicle Number and type");
        String licenseNumber,type;
        licenseNumber = scanner.next();
        type=scanner.next();
        slot = parkingSpace.leaveVehicle(licenseNumber,type);
        System.out.println("Vehicle Number "+ licenseNumber + "leave from slot "+slot[0] +" "+ slot[1]);

    }
    public void status(){
        parkingSpace.statusOfSPace();

    }
}
