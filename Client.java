import java.util.Scanner;

import command.Command;

public class Client {
    
    private static void userMenu(){
        System.out.println("1 create space");
        System.out.println("2 add vehicle");
        System.out.println("3 leave vehicle");
        System.out.println("4 status");

    }
    private static void executeOption(){
        String userOption;

        Scanner scanner = new Scanner(System.in);
        //userOption = scanner.nextLine();
        Command cmd = new Command();
        do {
            userOption = scanner.nextLine();
            switch (userOption) {
                case "1":   cmd.addFloor();                       
                            break;
                case "2": cmd.addVehicle();
                    break;
                case "3": cmd.leaveVehicle();
                            break;
                case "4": cmd.status();
                            break;
                case "0":break;
                default: System.out.println("Invalid Option");
            
            }
         }while (!userOption.equals("0") );
        scanner.close();

    }

    public static void main(String[] args) {
        userMenu();
        executeOption();
    }


}
