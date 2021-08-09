package parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import vehicle.Vehicle;


public class ParkingSpace {
    private int[][] parkingSpace;
    private int floorLevel;
    private int numberOfCars,numberOfBus,numberOfVan,numberOfBike;

    private final List<Vehicle> bikeList = new ArrayList<Vehicle>();
    private final List<Vehicle> carList = new ArrayList<Vehicle>();
    private final List<Vehicle> busList = new ArrayList<Vehicle>();
    private final List<Vehicle> vanList = new ArrayList<Vehicle>();

    int carCount=0,bikeCount=0,vanCount=0,busCount=0;

    Scanner scanner = new Scanner(System.in);

    public void parkingLevel(int floorNumber){
        this.floorLevel = floorNumber;
        this.parkingSpace = new int[floorNumber][4];
       
    }
    public void setUpEachLevel(int floorNumber){
        for(int i=0;i<floorNumber;i++){
            System.out.println("Level "+ i +" BIKE,CAR,VAN,BUS");
            for(int j=0;j<4;j++){
                parkingSpace[i][j] = scanner.nextInt();
            }
        }

       //total number of each vehicel is calculated
     numberOfBike = numberOfVehicle(floorNumber,0);
     numberOfCars = numberOfVehicle(floorNumber,1);
     numberOfVan = numberOfVehicle(floorNumber,2);
     numberOfBus = numberOfVehicle(floorNumber,3);
     System.out.println(numberOfBus);
     addSlotsInEachLevel(numberOfBike,"BIKE",bikeList);
     addSlotsInEachLevel(numberOfCars, "CAR", carList);
     addSlotsInEachLevel(numberOfVan, "VAN", vanList);
     addSlotsInEachLevel(numberOfBus, "BUS", busList);
    }
    private int numberOfVehicle(int floorNumber,int position){
        int sum=0;
        for (int i = 0; i < floorNumber; i++) {
            sum = sum+parkingSpace[i][position];
        }
        return sum;

    }

    public void addSlotsInEachLevel(int vehicleCount,String type,List<Vehicle> vehicleList){
        Vehicle vehicle = new Vehicle(type,type);
        for(int i=0;i<vehicleCount;i++){
            vehicleList.add(vehicle);

        }


    }

    private int[] findingSlotNumber(int position,int level){
        int[] slot = new int[3];
        int i;
        int sum = 0;
        for(i=0;i<floorLevel;i++){
            sum = sum + parkingSpace[i][position];
            if(sum>level){
                sum=sum - parkingSpace[i][position];
                slot[1] = level - sum;
                slot[0] = i;
                break;
            }
        }
        
        return slot;
    }

    public int[] parkVehicle(Vehicle vehicle,List<Vehicle> vehicleList,int vehicleCount,int numberOfVehicle,String type,int position){
        int[] slot = new int[3];
        if(vehicleCount<numberOfVehicle){
            for(int i=0;i<numberOfVehicle;i++){
                if(vehicleList.get(i).licenseNumber.equals(type)){
                    vehicleList.set(i, vehicle);
                    vehicleCount+=1;
                    slot = findingSlotNumber(position, i);
                    
                    break;

                }

            }
            
        }else{
            slot[0]=-1;
            slot[1]=-1;
        }
        slot[2]=vehicleCount;
        return slot;

    }

    public int[] addVehicleToParkingSpace(Vehicle vehicle){
        int[] slot =new int[3];
        if(vehicle.type.equals("CAR")){
           slot= parkVehicle(vehicle,carList,carCount,numberOfCars,"CAR",1);
           carCount=slot[2];          

        }else if (vehicle.type.equals("BUS")) {
            slot= parkVehicle(vehicle,busList,busCount,numberOfBus,"BUS",3);   
            busCount=slot[2];                 

        } else if(vehicle.type.equals("BIKE")) {
            slot= parkVehicle(vehicle,bikeList,bikeCount,numberOfBike,"BIKE",0); 
            bikeCount=slot[2];
        }else if(vehicle.type.equals("VAN")){
            slot= parkVehicle(vehicle,vanList,vanCount,numberOfVan,"VAN",2);
            vanCount=slot[2];         
        }
        return slot;
        
    }


    public int[] freeParkSpace(Vehicle vehicle,int vehicleCount,int numberOfVehicle,List<Vehicle> vehicleList,String licenseNumber,int position){
        int[] slot = new int[3];
       // if(vehicleCount<numberOfVehicle ){
            for (int i = 0; i < vehicleList.size(); i++) {
                if(vehicleList.get(i).licenseNumber.equals(licenseNumber)){
                  vehicleList.set(i,vehicle);
                  vehicleCount-=1;
                  slot = findingSlotNumber(position, i);
                  
                  break;
                }
                
            }

//        }
        slot[2]=vehicleCount;
        return slot;
    }


    public int[] leaveVehicle(String licenseNumber, String type){
        int[] slot =new int[3];
        Vehicle vehicle = new Vehicle(type,type);
        if(type.equals("BIKE")){
            slot = freeParkSpace(vehicle,bikeCount,numberOfBike,bikeList,licenseNumber,0); 
            bikeCount=slot[2];
        }else if(type.equals("CAR")){
            slot = freeParkSpace(vehicle,carCount,numberOfCars,carList,licenseNumber,1);  
            carCount=slot[2];
        }else if(type.equals("VAN")){
            slot = freeParkSpace(vehicle,vanCount,numberOfVan,vanList,licenseNumber,2);  
            vanCount=slot[2];
        }else if(type.equals("BUS")){
            slot = freeParkSpace(vehicle,busCount,numberOfBus,busList,licenseNumber,3); 
            busCount=slot[2]; 
        }else{
           slot[0]=-1;
           slot[1]=-1;
        }
        return slot;

    }


    public void statusOfSPace(){
        System.out.println("CAR");
        for (int i = 0; i < carList.size(); i++)
            System.out.print(carList.get(i).licenseNumber + " ");
    
        System.out.println();
        System.out.println("Bike");
        for (int i = 0; i < bikeList.size(); i++)
            System.out.print(bikeList.get(i).licenseNumber + " ");

        System.out.println();
        System.out.println("Van");
        for (int i = 0; i < vanList.size(); i++)
            System.out.print(vanList.get(i).licenseNumber + " ");

        System.out.println();
        System.out.println("Bus");
        for (int i = 0; i < busList.size(); i++)
            System.out.print(busList.get(i).licenseNumber + " ");

    }
}
