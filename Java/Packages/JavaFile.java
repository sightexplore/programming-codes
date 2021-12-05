package myjava;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


interface List{
    String[] listAll(String name);
    String[] listFile(String name);
    String[] listDir(String name);
}

interface Match{
    String[] groupFile(String name, String extension);
}

interface Remove{
    boolean deleteFile(String name);
    boolean deleteDir(String name);
}

interface Data{
    boolean fileExist(String name);
    void getFileData(String name);
    String getAbsolutePath(String name);
    double getSize(String name);
}

interface Create{
    boolean createFile(String name);
}

interface Write{
    boolean writeFile(String name, String data);
    boolean appendFile(String name, String data);
    boolean emptyFile(String name);
}

interface Read{
    int countLine(String name);
    String[] readFile(String name);
}


interface FileOperation extends Create, Data, List, Match, Remove, Write, Read{
    String NULL = "__null__";
}


public class JavaFile implements FileOperation{

    public boolean createFile(String name){
        boolean valid = true;
        try{
            File myObj = new File(name);
            myObj.createNewFile();
        }
        catch(IOException e){
            valid = false;
            e.printStackTrace();
        }
        return valid;
    }

    public boolean fileExist(String name){
        File myObj = new File(name);
        return myObj.exists();
    }

    public void getFileData(String name){
        File myObj = new File(name);
        if(myObj.exists()){
            System.out.println("\n"+name+"\n= = = = = = = = = =");
            System.out.println(" Absolute path: " + myObj.getAbsolutePath());
            System.out.println("     Writeable: " + myObj.canWrite());
            System.out.println("      Readable: " + myObj.canRead());
            System.out.println(" File size (B): " + myObj.length());
            System.out.println("= = = = = = = = = =");
        }
        else{
            System.out.println(name+" don't exist");
        }
    }

    public String[] listAll(String name){
        File myObj = new File(name);
        String[] list =  myObj.list();
        if(list.length == 0){ list[0] = NULL; }
        return list;
    }

    public String[] listFile(String name){
        File myObj = new File(name);
        File[] files = myObj.listFiles();

        int count = 0;

        try{
            for(File file : files){
                if (file.isFile()){ count++; }
            }
        }
        catch(NullPointerException e){
            // e.printStackTrace();
        }

        String[] fileX = new String[count];

        if(count != 0){
            fileX = new String[count];
            count = 0;
            for(File file : files){
                if (file.isFile()){ fileX[count++] = file.getName(); }
            }
        }
        else{
            fileX = new String[1];
            fileX[0] = NULL;
        }

        return fileX;
    }

    public String[] listDir(String name){
        File myObj = new File(name);
        File[] files = myObj.listFiles();

        int count = 0;

        try{
            for(File file : files){
                if (file.isDirectory()){ count++; }
            }
        }
        catch(NullPointerException e){
            // e.printStackTrace();
        }

        String[] dir;
        if(count != 0){
            dir = new String[count];
            count = 0;
            for(File file : files){
                if(file.isDirectory()){ dir[count++] = file.getName(); }
            }
        }
        else{
            dir = new String[1];
            dir[0] = NULL;
        }
        return dir;
    }

    public String getAbsolutePath(String name){
        File myObj = new File(name);
        String path = "";
        if(myObj.exists()){
            path = myObj.getAbsolutePath();
        }
        else{
            path = NULL;
        }
        return path;
    }

    public double getSize(String name){
        double size = 0.0;
        File myObj = new File(name);
        if(myObj.exists()){
            size = myObj.length();
        }
        return size;
    }

    public String[] groupFile(String name, String extension){
        File myObj = new File(name);
        File[] files = myObj.listFiles();

        int count = 0;
        try{
            for(File file : files){
                if (file.isFile() && file.getName().endsWith("."+extension)){
                    count++;
                }
            }
        }
        catch(NullPointerException e){
            // e.printStackTrace();
        }

        String[] fileX = new String[count];

        if(count != 0){
            fileX = new String[count];
            count = 0;
            for(File file : files){
                if (file.isFile() && file.getName().endsWith("."+extension)){
                    fileX[count++] = file.getName();
                }
            }
        }
        else{
            fileX = new String[1];
            fileX[0] = NULL;
        }

        return fileX;
    }

    public boolean deleteFile(String name){
        File myObj = new File(name);
        return myObj.delete();
    }

    public boolean deleteDir(String name){
        File myObj = new File(name);
        return myObj.delete();
    }

    public boolean writeFile(String name, String data){
        boolean written = false;
        try{
            FileWriter wrt = new FileWriter(name);
            wrt.write(data);
            wrt.close();
            written = true;
        }
        catch(IOException e){
            // e.printStackTrace();
        }
        return written;
    }

    public int countLine(String name){
        int line = 0;
        try{
            File myObj = new File(name);
            Scanner rdr = new Scanner(myObj);
            while(rdr.hasNextLine()){
                line++;
                rdr.nextLine();
            }
            rdr.close();
        }
        catch(FileNotFoundException e){
            // e.printStackTrace();
        }
        return line;
    }

    public String[] readFile(String name){
        int line = this.countLine(name);
        String[] data;
        try{
            File myObj = new File(name);
            Scanner rdr = new Scanner(myObj);
            
            if(line > 0){
                int count = 0;
                data = new String[line];
                while(rdr.hasNextLine()){
                    data[count++] = rdr.nextLine();
                }
            }else{
                data = new String[1];
                data[0] = NULL;
            }
        }
        catch(FileNotFoundException e){
            // e.printStackTrace();
            data = new String[1];
            data[0] = NULL;
        }
        
        return data;
    }

    public boolean appendFile(String name, String data){
        boolean appended = false;
        String[] prevData = this.readFile(name);

        if(prevData[0] != NULL || prevData.length > 1 ){
            String dataX = "";
            for(String x: prevData){ dataX += x+"\n"; }
            dataX += data;
            if(this.writeFile(name, dataX)){
                appended = true;
            }
        }

        return appended;
    }

    public boolean emptyFile(String name){
        boolean empty = false;
        if(this.writeFile(name, "")){ empty = true;}
        return empty;
    }
}
