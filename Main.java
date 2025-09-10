package org.example;
import java.io.File;
import java.io.IOException;
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        String filename="file.txt";
        // Create the file
        flcreation(filename);
        //Write data into the file
        filewriting(filename);
        // Read and display data from the file
        //filereading(filename);
        // no of time  each  word occurred
        cntword(filename);

    }
    //Method to create a file
    public static void flcreation(String filename){
        File n = new File(filename);
        try {
            //createNewFile(): creates a new empty file
            //returns true if new file created
            //returns false if file already exists
            if (n.createNewFile()) {
                System.out.println("Successfully file created");
            }else { // if file is already exist then else block execute
                System.out.println("File already exist");
            }
            
        } catch (IOException e) {
            System.out.println("error in creation");
        }
    }

    // Method to write data into the file
    public static void filewriting(String filename){
        try {

            Scanner sc = new Scanner(System.in);
            File f = new File(filename); // Create a File object
            FileWriter wrt = new FileWriter(f);  // FileWriter to write data
            System.out.println("Enter the data into file type exit to stop:");
            // Keep reading input lines until user types "exit"
            while (true) {
                String str = sc.nextLine();
                if (str.equalsIgnoreCase("exit")) {
                    System.out.println("Data successfully inserted into file");
                    break;
                }
                wrt.write(str + "\n"); //Write input into file
            }
            wrt.close(); // Close FileWriter then only data save into file
        }
        catch (IOException e)
        {
            System.out.println("Error in filewriting");
        }
    }
//    public static void filereading(String filename){
//
//        System.out.print("The data in the file :\n");
//        try{
//            File file=new File(filename);
//            Scanner sc=new Scanner(file);//scan data from file
//            while(sc.hasNextLine())
//            {
//                String str=sc.nextLine();
//                System.out.println(str);
//            }
//            sc.close();
//
//        }catch(FileNotFoundException e)
//        {
//            System.out.println("The file not found");
//        }
//    }
    //count no of times each word in file occur
    public static void cntword(String filename)
    {
        try{
            File file=new File(filename);
            Scanner sc=new Scanner(file);
            //Hashmap to store word counting of each word
            //key is a word
            //value used for counting
            Map<String,Integer>wordcnt=new HashMap<>();
            while(sc.hasNextLine())
            {
                String str=sc.nextLine();
                String []words=str.split(" ");
                for(String word:words) {
                    // If the word is already inside it return its value
                    //If the word is not inside then return 0
                    //increment the value
                    wordcnt.put(word, wordcnt.getOrDefault(word, 0) + 1);
                }
            }
            sc.close();
            System.out.println("\nNo of times each word appears in the file:");
            //wordcnt.keySet() - gets all unique words stored
            for(String key:wordcnt.keySet())
            {
                System.out.println(key+":"+wordcnt.get(key)+" time appear");
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }

    }

}
