/*
--> Name: Secret Encoding & Decoding BASE8
--> Code: Sight Explore
--> Date: 23rd April 2021
--> Version: v.0.0.1
*/
import java.util.Scanner;

class PasswordName
{

    void encodeName(String name){
        String encode = "";
        for(int i=0; i<name.length(); i++){
            char chr = name.charAt(i);
            chr = (char)(chr + 8);
            encode += chr;
            chr = (char)(chr - 16);
            encode += chr;
        }
        System.out.println("SECRET ID = " + encode);
    }

    void decodeName(String name){
        String decode = "";
        for(int i = 0; i < name.length(); i=i+2){
            char chr = name.charAt(i);
            chr = (char)(chr - 8);
            decode += chr;
        }
        System.out.println("VALUE OF SECRET ID = " + decode);
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("SECRET ENCODING & DECODING");
        System.out.println("PRESS 1: ENCODE ( Convert any NAME to SECRET ID ) ");
        System.out.println("PRESS 2: DECODE ( Convert the SECRET ID to NAME ) ");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        PasswordName obj = new PasswordName();

        switch(choice){
            case 1:
                System.out.print("Enter your the name: ");
                String name = sc.next();
                obj.encodeName(name);
                break;
            case 2:
                System.out.print("Enter your the secret id: ");
                String code = sc.next();
                obj.decodeName(code);
                break;               
            default:
                System.out.println("WRONG CHOICE !");
        }

        sc.close();
    }
}
