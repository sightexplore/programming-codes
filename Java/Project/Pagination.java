/*
    Java Program for Pagination:
    Divide the array of string into pages
    
    Input:
    - Array of String
    - Maximum data in a page
    - Page number
    
    Output:
    - Previous Page Number, if exist
    - Next Page Number, if exist
    - Current Page Data
*/

class Pagination
{
    private int count;
    private String data[];
    private int total;
    
    //Constructor
    Pagination(int count, String data[]){
        this.count = count;
        this.data = data;
        double up = ((double)data.length)/this.count;
        this.total = (int)(Math.ceil(up));
    }
    
    // To show Page items
    public void showPage(int start, int end, int page){
        System.out.println("\n╭["+page+"]: Page Data"); 
        for(int i=start; i<end; i++){
           System.out.println("├> "+this.data[i]);
        }
    }
    
    // To paginate items from array
    public void getPage(int page){
    
        // If page number is more than 0
        if(page > 0){
        
           // If the page doesn't exceed total page
           if(this.total >= page){
           
               /*
                 If single page exist and page == 1
                 - display all data
                 - no previous page
                 - no next page
               */
               if(this.total == 1){
                   showPage(0,this.data.length,page);
                   System.out.println("├•Prev: ░");
                   System.out.println("╰•Next: ░");
               }
               
               /*
                 If single page not exist and page == 1
                 - display first "n" items
                 - no previous page
                 - next page = 2
               */
               else if(page == 1){
                   showPage(0,this.count,page);
                   System.out.println("├•Prev: ░");
                   System.out.println("╰•Next: 2");
               }
               
               /*
                 Single page not exist and page == last
                 - display items: "n*(page-1)" to last
                 - previous page = page-1
                 - no next page
               */
               else if(this.total == page){
               System.out.println(this.total);
                  showPage(
                      this.count*(page-1),
                      this.data.length, page
                  );
                 System.out.println("├•Prev: "+(page-1));
                 System.out.println("╰•Next: ░");
               }
               
               /*
                 Single page not exist
                 - display items: 
                     - "n*(page-1)" to "n*(page)"
                 - previous page = page-1
                 - next page = page+1
               */
               else{
                  showPage(
                      this.count*(page-1),
                      this.count*(page), page
                  );
                 System.out.println("├•Prev: "+(page-1));
                 System.out.println("╰•Next: "+(page+1));
               }
           }else{
              System.out.println("\n× Page Not Exist"); 
           }
        }else{
          System.out.println("\n× Only +ive Page Exist");
        }
    }
    

    public static void main(String[] args){
    
        String json[] = {
            "💵", "💴", "💶",
            "💷", "💸", "🪙", 
            "💰", "💳", "💎", 
            "☢️"
        };
        int items = 2;
        
        Pagination obj = new Pagination(items,json);
        
        int startPage = -1;
        int endPage = 7;
        for(int i=startPage; i<=endPage; i++){
            obj.getPage(i);
        }
        
    }
}


/* 
  
 ╭─────────────────╮ 
 │    SIGHT EXPLORE   │ 
 ╰─────────────────╯ 
  
 ╭Detail 
 ├>Name: Pagination
 ├>Language: Java 
  
 ╭Analysis 
 ├>Time: O(1) 
 ├>Space: O(n) 
  
 ╭Topics 
 ├>Arrays(1D) 
 ├>Loops 
 ├>Function
 ├>Objects
  
 ╭─────────────────╮ 
 │ All right reserved │ 
 ╰─────────────────╯ 
 
*/
