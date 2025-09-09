package org.example;
import java.io.File;
import java.io.IOException;
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        //file creation
        File n = new File("TEXTFILE.txt");
        try {
            if (n.createNewFile()) {
                System.out.println("file created"); 
            } else {
                System.out.println("file already exist");
            }
            // Print the current working directory
            System.out.println("Current working directory: " + System.getProperty("user.dir"));
        } catch (IOException e) {
            System.out.println("error in creation");
        }


        //write into the files
        Scanner scd=new Scanner(System.in);
        System.out.println("Enter the data into the file  :");
        try {
            FileWriter writ = new FileWriter("TEXTFILE.txt", true);
            while(true)
            {
                String str=scd.nextLine();
                if(str.equalsIgnoreCase("exit")){
                    System.out.println("Data successfully inserted");
                    break;

            }
                writ.write(str+"\n");
            }
            writ.close();

        }catch(IOException e){
            System.out.print("error");
        }

// check the given target string how many times occur in  files
        try {
            File file = new File("TEXTFILE.txt");
            Scanner sc = new Scanner(file);
            System.out.println("Enter the target string:");
            String target=scd.nextLine();
            int cnt=0;
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String []arr=s.split("\\es+");
                for(String ss:arr) {
                    if (ss.equals(target)) {
                        cnt++;
                    }
                }
            }
            System.out.print(cnt+":time string occurred in file ");
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.print("error");
        }
    }

}
