#include<iostream>
#include <windows.h>
#include <cstring>
#include <fstream>
#include <vector>
#include <ctime>
using namespace std;

class User{
    protected:
        fstream fptr, ftemp;
        bool loggedIn = false;
        bool endSegmet = false;
        string currentUser;

    public:
        User(){
            cout << "WELCOME !" << endl;
            menuDisplay();
        }

        void setCurrentUserSession(string name){
            currentUser = name;
        }

        string getCurrentUserSession(){
            return currentUser;
        }

        void menuDisplay(void){
            int choice;
            cout << "PRESS 1: CREATE NEW ACCOUNT" << endl;
            cout << "PRESS 2: LOGIN " << endl;
            cout << "PRESS 0: TERMINATE" << endl;
            cout << "PRESS ANY OTHER KEY FOR --HELP" << endl;
            cout << "========================================" << endl;
            cout << "ENTER YOUR CHOICE: ";
            cin >> choice;

            if(choice == 0){ endTask(); }
            else if(choice == 1){ signUp(); }
            else if(choice == 2){ logIn(); }
            else{ help(); }
        }

        bool checkString(string name){
            bool valid = true;
            for(int i=0; i<name.length(); i++){
                if(isupper(name[i]) || islower(name[i]) || isdigit(name[i])){
                    //pass
                }else{
                    valid = false;
                }
            }
            return valid;
        }

        vector<string> parseCsvByLine(string line, string tokenize = ", "){

            vector<string> value;
            string cache = "";
            int tlen = tokenize.length();

            line += tokenize;

            for(int i = 0; i < line.length()-tlen+1; i++){
                string temp = line.substr(i,tlen);
                if(temp == tokenize){
                    value.push_back(cache);
                    cache = "";
                    i += tlen;
                }
                cache += line[i];
            }

            return value;
        }

        void signUp(void){
            string username, password, msg;
            cout << endl;
            cout << "SIGN UP" << endl;
            cout << "USERNAME AND PASSWORD SHOULD NOT EXCEED 15 CHARACTER " << endl;
            cout << "MUST CONTAIN ONLY [A-Z], [a-z] and [0-9]" << endl;
            cout << "=========================================================" << endl;
            cout << "ENTER USERNAME: ";
            cin >> username;
            cout << "\nENTER PASSWORD: ";
            cin >> password;
            msg = setUserPassword(username, password);

            if(msg == "PASS"){
                setCurrentUserSession(username);
            }else{
                cout << "ERROR: "<< msg << endl;
                cout << "PLEASE TRY AGAIN " << endl;
                signUp();
            }
        }

        void authenticateUser(string user, string pass){

            fptr.open("user.csv", ios::in);
            vector<string> data;
            string line, value;

            while(true){
                getline(fptr, line);
                if (line != "") {
                    //cout << line << endl;

                    data = parseCsvByLine(line);
                    for(auto &i: data){
                        if(data[0] == user && data[1] == pass){
                            loggedIn = true;
                        }
                    }
                    data.clear();
                }
                else { break; }
            }
            fptr.close();
        }

        bool usernameExist(string user){
            fptr.open("user.csv", ios::in);
            bool exist = false;
            vector<string> data;
            string line, value;

            while(true){
                getline(fptr, line);
                if (line != "") {
                    //cout << line << endl;

                    data = parseCsvByLine(line);
                    for(auto &i: data){
                        if(data[0] == user){
                            exist = true;
                            break;
                        }
                    }
                    data.clear();
                }
                else { break; }
            }

            fptr.close();
            return exist;
        }

        string setUserPassword(string user, string pass){
            string msg = "PASS";
            bool valid = true;

            //cout << "\n ( " << user << ", " << pass << ") " << endl;
            if(user.length() >= 16 && pass.length() >= 16){
                msg = "LENGTH SHOULD NOT EXCEED 15 CHARACTER";
            }else{
                if(checkString(user) && checkString(pass)){
                    if(usernameExist(user)){
                        msg = "THIS USERNAME ALREADY EXIST! TRY SOMETHING NEW";
                    }else{
                        fptr.open("user.csv", ios::out | ios::app);
                        fptr << user << ", " << pass << "\n";
                        fptr.close();
                        //cout << "\n\n\n\n IT SHOULD WORK \n\n\n\n\n";
                    }
                }else{
                    msg = "MUST CONTAIN ONLY [A-Z], [a-z] and [0-9]";
                }
            }
            return msg;
        }

        void logIn(void){
            string user, pass;

            cout << endl;
            cout << "LOGIN" << endl;
            cout << "=================" << endl;
            cout << "ENTER USERNAME: ";
            cin >> user;
            cout << "\nENTER PASSWORD: ";
            cin >> pass;

            authenticateUser(user, pass);

            if(loggedIn){
                setCurrentUserSession(user);
            }else{
                cout << "ERROR: WRONG CREDENTIALS! PLEASE TRY AGAIN" << endl;
                logIn();
            }
            
        }

        void help(void){
            cout << "HELP" << endl;
        }

        void endTask(void){
            endSegmet = true;
            cout << "\nENDTASK\n" << endl;
        }

        ~User(){
            cout << "\nTHANK YOU !\n" << endl;
        }
};

class List: public User{

    public:
        List(): User(){
            if(endSegmet){
                cout << "==============================" << endl;
            }
            else if(loggedIn){
                cout << "LOGGED IN" << endl;
                userAccount();
            }
            else{
                menuDisplay();
            }
        }

    void addTask(string task){

        time_t now = time(0);
        string dt = ctime(&now);

        fptr.open("task.csv", ios::out | ios::app);
        fptr << currentUser << ", " << task << ", " << dt;
        fptr.close();

    }

    void showAllTask(void){
        int count = 0;
        vector<vector<string>> allTask;
        vector<string> data;
        string line;

        fptr.open("task.csv", ios::in);
        while(true){
            getline(fptr, line);
            if (line != "") {
                data = parseCsvByLine(line);
                if(data[0] == currentUser){
                    allTask.push_back(data);
                    count++;
                }
                data.clear();
            }
            else { break; }
        }
        fptr.close();

        if(count){
           cout << "SL.No \t [ DATE - TIME ] \t\t [ TASK ] " << endl;
           int x = 0;
           for(auto &i: allTask){
                cout << ++x << "\t" << i[2] << "\t" << i[1] << endl;
           }
        }else{
            cout << "No Task left" << endl;
        }
        
    }

    bool deleteTask(int sl){

        bool found = false;
        int count = 0;
        vector<string> data;
        vector<string> dataVal;
        string line;

        fptr.open("task.csv", ios::in);
        while(true){
            getline(fptr, line);
            if (line != "") {
                data = parseCsvByLine(line);
                if(data[0] == currentUser){
                    dataVal.push_back(line);
                    count++;
                }
                data.clear();
            }
            else { break; }
        }
        fptr.close();
        

        if( sl <= count && sl >= 0 ){
            found = true;
            string val = dataVal[sl-1];
            string updateVal = "";

            fptr.open("task.csv", ios::in);
            while(true){
                getline(fptr, line);
                if (line != "") {
                    if(line != val){
                        updateVal += line + "\n";
                    }
                }
                else { break; }
            }
            fptr.close();

            ftemp.open("task_update.csv", ios::out);
            ftemp << updateVal;
            ftemp.close();

            remove("task.csv");
            rename("task_update.csv", "task.csv");

        }

        fptr.close();
        return found;
    }

    void userAccount(void){
        int choice;
        cout << "========================================" << endl;
        cout << "WELCOME " << currentUser << " !" << endl;
        cout << "========================================" << endl;
        cout << "PRESS 1: Add Task " << endl; 
        cout << "PRESS 2: View All Task " << endl;
        cout << "PRESS 3: Delete Task " << endl;
        cout << "PRESS 0: To logout " << endl;
        cout << "========================================" << endl;
        cout << "Enter your choice: ";
        cin >> choice;

        if(choice == 1){
            string task;

            cout << endl;
            cout << "Enter your new task: ";

            getline(cin, task); //Skips '\n'
            getline(cin, task); //Acccepts 

            addTask(task);
            userAccount();

        }
        else if(choice == 2){
            
            cout << endl;
            cout << "All your task" << endl;
            cout << "=======================" << endl;
            showAllTask();
            cout << "\n\n" << endl;

            userAccount();

        }
        else if(choice == 3){
            int num;

            cout << endl;
            cout << "All your task" << endl;
            cout << "=======================" << endl;
            showAllTask();
            cout << "\n\n" << endl;

            cout << "Enter the Serial No. of task to be deleted: ";
            cin >> num;

            if(deleteTask(num)){
                cout << "Record Deleted \n\n" << endl;
            }else{
                cout << "Invalid Input ! \n\n" << endl;
            }

            userAccount();

        }else{
            endTask();
        }
    }
};

int main(){

    List obj;

    return 0;
}