import java.util.Scanner;
class Flames
{
    //instance variable
    String name1, name2;
    char letter[] = {'F', 'L', 'A', 'M', 'E', 'S'};
    String status[] = {"Friends", "Lover", "Affectionate", "Marriage", "Enemies", "Siblings"};
    
    void input()
    {
        Scanner in = new Scanner(System.in);  //Creating Scanner Object
        System.out.print(" Enter first name : ");
        name1 = in.nextLine().toUpperCase().replace(" ", "");   //input->Uppercase->Remove Space
        System.out.print("Enter second name : ");
        name2 = in.nextLine().toUpperCase().replace(" ", "");;
        in.close();                         //Closing Scanner Object
    }
    
    int strikeCommon()
    {
        String copy1 = name1;
        String copy2 = name2;
        int totalLength = name1.length()+name2.length();
        
        System.out.print("\nCOMMON LETTERS: ");
        for (int i=0; i < copy1.length() ; i++)
        {
            for (int j=0; j < copy2.length(); j++)
            {
                char chr1 = copy1.charAt(i);
                char chr2 = copy2.charAt(j);
                if(chr1 == chr2)
                {
                    System.out.print(chr1+" ");
                    totalLength -= 2; //both letter striked out
                    copy1 = copy1.substring(0, i)+"*"+copy1.substring(i+1);
                    copy2 = copy2.substring(0, j)+"*"+copy2.substring(j+1);
                    break;
                }
            }
        }
        return totalLength;
    }
    
    void strikeFlames(int len)
    {
        System.out.print("\n        RESULT: ");
        if(len == 0)    //Exception where answer is = F * * * * *
        {
            for(int i=1; i<6; i++)
                letter[i] = '*';
        }else
        {
            int pos = -1;
            for (int i=0; i<5; i++)
            {
                int count = 0;
                while(count != len)
                {
                    pos = (pos+1)%6;
                    if(letter[pos] != '*')
                        count++;
                }
                letter[pos] = '*';
            }
        }
        for (int i=0; i<6 ; i++ )
            System.out.print(letter[i]+" ");
    }
    
    void printStatus()
    {
        for (int i=0; i<6 ; i++ )
        {
            if(letter[i] != '*')
                System.out.print("\n        STATUS: "+ status[i]);
        }
    }
    
	public static void main (String[] args)
	{
		Flames obj = new Flames();
		obj.input();                                // Takes Input of two names
		int uncommonLetter = obj.strikeCommon();    // Cancel out common Letters
		obj.strikeFlames(uncommonLetter);           // Strike out the letter of FLAMES
		obj.printStatus();                          // Print the appropriate Status 
	}
}
