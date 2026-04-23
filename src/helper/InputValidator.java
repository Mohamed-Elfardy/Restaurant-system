package helper;

import java.util.Scanner;
public class InputValidator {
    private static Scanner input = new Scanner(System.in);
    
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
                System.out.println(message);
                choose = Double.parseDouble(input.nextLine());
                return choose;
            }
            catch (NumberFormatException n){
                System.out.println("Write Only a Number!");
            }
        }
    }

    // String Validation
    public static String Read_String(String message){
        String choose;
        while(true){
            System.out.println(message);
            choose = input.nextLine().trim();    // .trim() to remove more spaces

            if(!choose.isBlank())
                return choose;
            else{
                System.out.println("Your Choose cannot be Empty or Spaces");
            }
        }
    }


}
