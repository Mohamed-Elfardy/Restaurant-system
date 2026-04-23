package helper;

import java.util.Scanner;
public class InputValidator {
    private static final Scanner input = new Scanner(System.in);
    
    // Integer Validation
    public static int Read_Int(String message){
        int choose;
        while(true){
            try{
                System.out.print(message);
                choose = Integer.parseInt(input.nextLine());
                return choose;
            }
            catch (NumberFormatException n){
                System.out.println("Write Only a Number!");
            }
        }
    }
    
    // Double Validation
    public static double Read_Double(String message){
        double choose;
        while(true){
            try{
                System.out.print(message);
                choose = Double.parseDouble(input.nextLine());
                return choose;
            }
            catch (NumberFormatException n){
                System.out.println("Write Only a Number!");
            }
        }
    }

    // String Validation
    public static String Read_String(String message) {
        String choose;
        while (true) {
            System.out.print(message);
            choose = input.nextLine().trim(); // .trim() to remove more spaces

            if (!choose.isBlank())
                return choose;
            else {
                System.out.println("Your choice cannot be empty or spaces");
            }
        }
    }
    

    // If Range is existed
    // public static int readIntInRange(int min, int max, String message) { ... }
    // public static double readPositiveDouble(String message) { ... }


}
