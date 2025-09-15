package org.example;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<FileResult> list = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\dhaya\\Filelist\\File.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String filepath = sc.nextLine().trim();
                if (!filepath.isEmpty()) {
                    //Call method to calculate word count
                    FileResult result = calculateCount(filepath);
                    //Add the result to the list
                    list.add(result);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.print("Error");
        }
         // Print execution time and total words for each file
        for (FileResult obj : list ) {
            System.out.println("Execution time:" + obj.executionTime + "ms");
            System.out.println("Total words:" + obj.totalWords);
            System.out.println("\n");
        }
        System.out.println("\nWord count details:");
        for(FileResult obj : list ) { 
            for (String str : obj.wordCount.keySet() ) {
                System.out.println(str + " : " + obj.wordCount.get(str));
            }
            System.out.println("-------------------------");
        }
    }
    // Method to count words in a file
    private static FileResult calculateCount(String filepath) {
        int totalWords = 0;
        long startTime = System.currentTimeMillis();//starting time
        Map<String, Integer> wordCount = new HashMap<>();

        try {
            File file = new File(filepath);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] words = str.split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        totalWords++;
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found ");
        }

        long endTime = System.currentTimeMillis();//ending time
        long executionTime = endTime - startTime;//executiontime

        return new FileResult(totalWords, executionTime, wordCount); //return the fileresult object to caller
    }
    
    //Userdefined class
    static class FileResult { 
        int totalWords;
        long executionTime;
        Map<String, Integer> wordCount;
        FileResult(int totalWords, long executionTime, Map<String, Integer> wordCount) {
            this.totalWords = totalWords;
            this.executionTime = executionTime;
            this.wordCount = wordCount;
        }
    }
} 
