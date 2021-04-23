import java.util.Scanner;

class DigitalNumber
{
    public static void main(String []args)
    {
        Scanner sc = new Scanner(System.in);
        //User Input
        System.out.print("Enter the Single digit number: ");
        int num = sc.nextInt();

        System.out.print("Enter the character: ");
        char chr = sc.next().charAt(0);

        //Check the Input
        if(num >=0 && num <= 9)
        {
            System.out.println("\n The Digital Display of the number is \n");
            char x[] = new char[25];
            for(int i = 0; i<25; i++){
                x[i] = chr;
            }
            
            //Digit Encoding

            //Dont care condition
            x[6] = ' ';
            x[8] = ' ';
            x[16] = ' ';
            x[18] = ' ';

            //Individual encoding
            if(num == 0){ x[7] = ' '; x[11] = ' '; x[12] = ' '; x[13] = ' '; x[17] = ' ';}
            if(num == 1){ x[0] = ' '; x[3] = ' '; x[4] = ' '; x[5] = ' '; x[9] = ' ';x[10] = ' ';x[11] = ' ';x[13] = ' ';x[14] = ' ';x[15] = ' ';x[19] = ' ';x[20] = ' ';x[21] = ' ';x[23] = ' ';x[24] = ' ';}
            if(num == 2){ x[5] = ' '; x[7] = ' '; x[17] = ' '; x[19] = ' ';}
            if(num == 3){ x[5] = ' '; x[7] = ' '; x[10] = ' '; x[11] = ' ';x[15] = ' '; x[17] = ' ';}
            if(num == 4){ x[1] = ' '; x[2] = ' '; x[3] = ' '; x[7] = ' '; x[15] = ' ';x[17] = ' '; x[20] = ' ';x[21] = ' ';x[22] = ' ';x[23] = ' ';}
            if(num == 5){ x[7] = ' '; x[9] = ' '; x[15] = ' '; x[17] = ' ';}
            if(num == 6){ x[7] = ' '; x[9] = ' '; x[17] = ' ';}
            if(num == 7){ x[5] = ' '; x[7] = ' '; x[10] = ' ';x[11] = ' '; x[12] = ' '; x[13] = ' ';x[15] = ' '; x[17] = ' '; x[20] = ' ';x[21] = ' '; x[22] = ' '; x[23] = ' ';}
            if(num == 8){ x[7] = ' '; x[17] = ' ';}
            if(num == 9){ x[7] = ' '; x[15] = ' '; x[17] = ' ';}


            //Display
            for(int i=0; i<5; i++)
            {
                for(int j=0; j<5; j++)
                {
                    System.out.print(x[i*5+j]+" ");
                }
                System.out.println();
            }
        }
        else
        {
            System.out.println("Invalid please enter a single digit !");
        }
        sc.close();
    }
}
