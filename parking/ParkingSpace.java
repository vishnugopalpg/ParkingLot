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
        //System.out.println(this.parkingSpace[0][0]);
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
        int[] slot = new int[2];
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

    public int[] addVehicleToParkingSpace(Vehicle vehicle){
        int[] slot =new int[2];
        if(vehicle.type.equals("CAR")){
           
            if(carCount<numberOfCars){
                for(int i=0;i<numberOfCars;i++){
                    if(carList.get(i).licenseNumber.equals("CAR")){
                        carList.set(i, vehicle);
                        carCount+=1;
                        slot = findingSlotNumber(1, i);
                        break;
    
                    }
    
                }
                
            }else{
                System.out.println("No slot Availble");
            }
            
            /*if(carCount<numberOfCars){
                carList.add(carCount,vehicle);
                carCount+=1;
                slot= findingSlotNumber(1,carCount);
            }else{
                System.out.println("No slot Availble");
            }*/
            
            

        }else if (vehicle.type.equals("BUS")) {
           
            if(busCount<numberOfBus){
                for(int i=0;i<numberOfBus;i++){
                    if(busList.get(i).licenseNumber.equals("BUS")){
                        busList.set(i, vehicle);
                        busCount+=1;
                        slot = findingSlotNumber(3, i);
                        break;
    
                    }
    
                }
                
            }else{
                System.out.println("No slot Availble");
            }
           /* if(busCount<numberOfBus){
                busList.add(vehicle);
                busCount+=1;
                slot= findingSlotNumber(3,busCount);
            }else{
                System.out.println("No slot Availble");
            }*/
            

        } else if(vehicle.type.equals("BIKE")) {
            
            if(bikeCount<numberOfBike){
                for(int i=0;i<numberOfBike;i++){
                    if(bikeList.get(i).licenseNumber.equals("BIKE")){
                        bikeList.set(i, vehicle);
                        bikeCount+=1;
                        slot = findingSlotNumber(0, i);
                        break;
    
                    }
    
                }
                
            }else{
                slot[0]=-1;
                slot[1]=-1;
                System.out.println("No slot Availble");
            }
            /*if(bikeCount<numberOfBike){
                bikeList.add(vehicle);
               // System.out.println(bikeCount);
                bikeCount+=1;
                slot= findingSlotNumber(0,bikeCount);
            }else{
                System.out.println("No slot Availble");
            }
            */
        }else if(vehicle.type.equals("VAN")){
            
            if(vanCount<numberOfVan && bikeCount>0){
                for(int i=0;i<numberOfVan;i++){
                    if(vanList.get(i).licenseNumber.equals("VAN")){
                        vanList.set(i, vehicle);
                        vanCount+=1;
                        slot = findingSlotNumber(2, i);
                        break;
    
                    }
    
                }
                
            }else{
                System.out.println("No slot Availble");
            }
            /*if(vanCount<numberOfVan){
                vanList.add(vehicle);
                vanCount+=1;
                slot= findingSlotNumber(2,vanCount);
            }else{
                System.out.println("No slot Availble");
            }*/
            
        }
        return slot;
        
    }


    public int[] leaveVehicle(String licenseNumber, String type){
        int[] slot =new int[2];
        if(type.equals("BIKE")){
            if(bikeCount<numberOfBike && bikeCount>0){
                for (int i = 0; i < bikeList.size(); i++) {
                    if(bikeList.get(i).licenseNumber.equals(licenseNumber)){
                        Vehicle vehicle = new Vehicle(type,type);
                        bikeList.set(i,vehicle);
                      slot = findingSlotNumber(0, i);
                      bikeCount-=1;
                        break;
                    }
                    
                }
    
            }
            
        }else if(type.equals("CAR")){
            if(carCount<numberOfCars && carCount>0){
                for (int i = 0; i < carList.size(); i++) {
                    if(carList.get(i).licenseNumber.equals(licenseNumber)){
                        Vehicle vehicle = new Vehicle(type,type);
                        carList.set(i,vehicle);
                      slot = findingSlotNumber(1, i);
                      carCount-=1;
                        break;
                    }
                    
                }
            }
            

        }else if(type.equals("VAN")){
            if(vanCount<numberOfVan && vanCount>0){
                for (int i = 0; i < vanList.size(); i++) {
                    if(vanList.get(i).licenseNumber.equals(licenseNumber)){
                        Vehicle vehicle = new Vehicle(type,type);
                        vanList.set(i,vehicle);
                      slot = findingSlotNumber(2, i);
                      vanCount-=1;
                        break;
                    }
                    
                }
            }
            
        }else if(type.equals("BUS")){
            if(busCount<numberOfBus && busCount>0){
                for (int i = 0; i < busList.size(); i++) {
                    if(busList.get(i).licenseNumber.equals(licenseNumber)){
                        Vehicle vehicle = new Vehicle(type,type);
                        busList.set(i,vehicle);
                      slot = findingSlotNumber(0, i);
                      busCount-=1;
                        break;
                    }
                    
                }
            }
            
        }else{
            System.out.println("Invalid type of vehicle");
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
