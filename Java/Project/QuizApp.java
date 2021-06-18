import java.util.Scanner;
import java.io.PrintWriter;

class QuizApp
{
    Scanner sc = new Scanner(System.in);
    PrintWriter sout = new PrintWriter(System.out, true);

    String data[] = new String[10];
    int question[] = new int[10];
    boolean show = true;
    int score = 0, positivePoints = 1, negativePoints = 0;

    QuizApp(){
        this.startQuiz();
    }

    QuizApp(int positivePoints, int negativePoints, boolean show){
        this.positivePoints = positivePoints;
        this.negativePoints = negativePoints;
        this.show = show;
        this.startQuiz();
    }

    void startQuiz(){
        this.prepareQuestion();
        this.randomiseQuestion();
        this.extractQuestion();
        this.displayScore();
    }
    
    void prepareQuestion(){
        //Format: --- => Seperator, [ Question, op1, op2, op3, op4, correct] 
        data[0] = "1 What is the capital of India ?---Mumbai---Delhi---Punjab---Kolkata---Delhi";
        data[1] = "2 What is the capital of India ?---Mumbai---Delhi---Punjab---Kolkata---Delhi";
        data[2] = "3 What is the capital of India ?---Mumbai---Delhi---Punjab---Kolkata---Delhi";
        data[3] = "4 What is the capital of India ?---Mumbai---Delhi---Punjab---Kolkata---Delhi";
        data[4] = "5 What is the capital of India ?---Mumbai---Delhi---Punjab---Kolkata---Delhi";
        data[5] = "6 What is the capital of India ?---Mumbai---Delhi---Punjab---Kolkata---Delhi";
        data[6] = "7 What is the capital of India ?---Mumbai---Delhi---Punjab---Kolkata---Delhi";
        data[7] = "8 What is the capital of India ?---Mumbai---Delhi---Punjab---Kolkata---Delhi";
        data[8] = "9 What is the capital of India ?---Mumbai---Delhi---Punjab---Kolkata---Delhi";
        data[9] = "10 What is the capital of India ?---Mumbai---Delhi---Punjab---Kolkata---Delhi";
    }

    void randomiseQuestion(){
        
        int serialArray[] = new int[10];
        int maxVal = serialArray.length;
        int quest, count = 0;

        for(int i = 0; i < maxVal; i++) { serialArray[i] = i; }

        while(true){
            quest = (int)(Math.random()*(maxVal+1));

            if(newValueCheck(serialArray,quest)){ 
                question[count++] = quest;
                serialArray[quest] = -1;
            }

            if(count == maxVal){
                break;
            } 
        }
    }

    boolean newValueCheck(int arr[], int num){
        boolean check = false;
        for(int i: arr){
            if(num == i) { check = true; }
        }
        return check;
    }

    void extractQuestion(){

        int count = 1;
        for(int i: question){
            String encoded[] = data[i].split("---",6);
            this.presentQuestion(count++, encoded);
        }

    }

    void presentQuestion(int num,String[] quest){
        sout.println("\n\n" + num + ">> " + quest[0]);
        sout.println("1) "+ quest[1] + "\t" + "2) " + quest[2] + "\t" + "3) " + quest[3] + "\t" + "4) " + quest[4] + "\n ");
        sout.println("ENTER YOUR CHOICE : ");
        
        String choice = sc.next();
        char charChoice = choice.charAt(0);

        if(charChoice == '1' || charChoice == '2' || charChoice == '3' || charChoice == '4'){
            int intChoice = ((int)(charChoice)) - 48;
            if(quest[intChoice].equals(quest[5])){
                sout.println("\n Correct Choice !");
                score += this.positivePoints;
            }
            else{
                sout.println("\n xxxx Wrong Choice xxxxx !");
                score -= this.negativePoints;
                if(this.show) {  sout.println(" Correct one will be : "+quest[5]);  }
            }
        }
        else{
            sout.println("\n xxxx Wrong Button Selected xxxxx !");
            if(this.show) {  sout.println("Be Very Careful, Correct one will be : "+quest[5]);  }
            score -= this.negativePoints;
        }

        sout.println("============================================");
    }

    void displayScore(){
        sout.println("\n\n Your Score = "+score);
    }

    public static void main(String []args){

        //QuizApp play = new QuizApp();
        QuizApp play = new QuizApp(10,2,false);
    }
}
