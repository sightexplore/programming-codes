// Custom Type: { String[], int[] }
class Data{
    public String name[] = {};
    public int value[] = {};
    
    Data(String name[], int value[]){
        if(name.length == value.length){
            this.name = name;
            this.value = value;
        }
    }
}


// Plotting the Chart
class LinearScale
{
    private String plot;
    private int screen;
    private Data data;
    private String title = "Chart Title";
    private int size = 0;
    
    // Constructor 1
    LinearScale(String plot, int screen, Data data){
        this.plot = plot;
        this.screen = screen;
        this.data = data;
        this.size = this.data.value.length;
    }
    
    // Constructor 2
    LinearScale(Data data){
        this.plot = "💰";
        this.screen = 20;
        this.data = data;
        this.size = this.data.value.length;
    }
    
    // To set title
    public void setTitle(String title){
        this.title = title;
    }
    
    // To get length of maximum string
    public int getMaxName(){
        int max = this.data.name[0].length();
        for(int i=1; i<this.size; i++){
            max = (
                     this.data.name[i].length() > max
                  )? this.data.name[i].length() : max;
        }
        return max;
    }
    
    // To get the maximum value
    public int getMaxValue(){
        int max = this.data.value[0];
        for(int i=1; i<this.size; i++){
            max = (
                     this.data.value[i] > max
                  )? this.data.value[i]: max;
        }
        return max;
    }    
    
    // To print Chart Title
    public void makeTitle(){
        System.out.println(this.title.toUpperCase());
        for(int i=0; i<this.title.length(); i++){
            System.out.print("═");
        }
        System.out.println();
    }
        
  // Print Chart
  public void makeChart(){
    double scale = (double)getMaxValue()/this.screen;  
    int maxLength = getMaxName();
    
    makeTitle();  
    for(int i=0; i<this.size; i++){
        
       double val = Math.round(this.data.value[i]/scale);
       int pad = maxLength-this.data.name[i].length();
            
            for(int j=0; j<pad; j++){
                System.out.print(" ");
            }
            System.out.print(this.data.name[i]+" ├");
            for(int j=0; j<(int)val; j++){
                System.out.print(this.plot);
            }
            System.out.println();
    }
    System.out.println();
  }
}



class Scaling
{
    public static void main(String[] args) {
    
        // Dataset 
        int data[] = {19,20,80,78,70,70};
        String name[] = {
            "Grain",
            "Rice",
            "Corn",
            "Barley",
            "Fishery",
            "Dairy"
        };
        Data set = new Data(name, data);
    
        // Create object 
        LinearScale x = new LinearScale(set);
        x.makeChart();
        
        // Create another object
        int screen = 15;
        String plot = "🌳";
        LinearScale y = new LinearScale(plot,screen,set);
        y.setTitle("Economical Farming");
        y.makeChart();
    }
}





/*

╭─────────────────╮
│    SIGHT EXPLORE   │
╰─────────────────╯

╭Detail
├>Name: Scaling
├>Language: Java

╭Analysis
├>Time: O(n)
├>Space: O(n)

╭Topics
├>Arrays(1D)
├>Loops
├>Function
├>Class
├>Object

╭─────────────────╮
│ All right reserved │
╰─────────────────╯

License: https://github.com/sightexplore/programming-codes/blob/main/LICENSE

*/








