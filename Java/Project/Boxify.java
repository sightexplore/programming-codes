class Boxify
{

    // Comparing String length with number
    public int greater(String x, int y){
        if(x.length() > y) return x.length();
        return y;
    }
    
    
    // For loop ++: Printing String Value
    public void loop(int x, int y, String val){
        for(int j=x; j<y; j++) System.out.print(val);
    }
    
    
    // To make top and bottom border
    public void makeBorder(
        String x, String y, int arr[]
     ){
        /*
           Adjust Factor 
           =============
           
           x = | 1234 | 123456 | 12 |
           where 1234,123456,12 are max width length
           
           The adjust factor will count extra " " and "|"
           If no.of column = "n"
           no.of " " = "2n"
           no.of "|" = "n+1"
           adjust = "2n+n+1" = "3n+1"
           
           As "|" is printed explicitly at begin and end
           adjust = "3n+1-2" = "3n-1"
        */
        int adjust = arr.length*3-1;
        System.out.print(x);
        for(int i=0; i<arr.length; i++){ 
           loop(0,arr[i],"-");
        }
        loop(0,adjust,"-");
        System.out.println(y);
    }
    
    
    // Make Table
    public void makeTable(String Data[][]){
    
        // Calculate no.of column
        int column = Data[0].length;
        // Initailize max width of column 
        int[] width = new int[column];
        
        // Find greatest string length in each column
        for(int i=0; i<Data.length; i++){
            for(int j=0; j<Data[i].length; j++){
                // Set max width of "j-th" column
                width[j] = greater(Data[i][j], width[j]);
            }
        }
        
        // Bordering Top (Above Heading)
        makeBorder("┌","┐",width);
        
        for(int i=0; i<Data.length; i++){
        
            // Bordering Middle (Below Heading)
            if(i == 1){ makeBorder("├","┤",width); }
            
            // START: Bordering Data
            System.out.print("│ ");
            for(int j=0; j<Data[i].length; j++){
                /*
                   Calculate empty space to fill (pad)
                   width[j] : max column width
                   Data[i][j] : present string
                */ 
                int pad = width[j] - Data[i][j].length();
                
                // DATA PRINT: If 1st row then uppercase
                if(i==0){
                   System.out.print(
                      Data[i][j].toUpperCase()
                   );
                }else{
                   System.out.print(Data[i][j]);
                }
                
                // Add padded value last (left Align)
                /*
                  - Used before DATA PRINT (right Align)
                  - Half used before and half used after 
                    DATA PRINT (center Align)
                */
                loop(0,pad," ");
                System.out.print(" │ ");
            }
            System.out.println();
            // END: Bordering Data
            
        }
        
        // Bordering Bottom (Below Last Data)
        makeBorder("└","┘",width);
    }
    
    
    public static void main(String[] args){
        
        // Create object of Boxify (As it is non-static)
        Boxify dataObj1 = new Boxify();
        
        // Data 1
        String Data[][] = {
            {"Sl.", "name", "Age"},
            {"1", "Derek", "23"},
            {"2", "Shayama", "39"}
        };
        
        // Boxifing Data1
        dataObj1.makeTable(Data);
        
        
        // Data 2
        String Head2[] = {"code", "Subject", " Student"};
        String Data2[][] = {
            Head2,
            {"CS", "Computer Science", "56"},
            {"IT", "Information Technology", "45"},
            {"ME", "Mechanical Engineering", "60"}
        };
        
        // Boxifying Data 2
        dataObj1.makeTable(Data2);
        
    }
}


/*

╭─────────────────╮
│    SIGHT EXPLORE   │
╰─────────────────╯

╭Detail
├>Name: Boxify
├>Language: Java

╭Analysis
├>Time: O(n^2)
├>Space: O(n)

╭Topics
├>Arrays(1D,2D)
├>Loops
├>Function

╭─────────────────╮
│ All right reserved │
╰─────────────────╯

*/
