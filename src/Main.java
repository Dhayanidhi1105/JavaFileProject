package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Main Class
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Display menu options
        System.out.println("               --- Welcome to File Analyzer ---           ");
        System.out.println("Enter the filepath (this file should contain list of files to analyze): ");
        String filepath = sc.nextLine();

        System.out.println("\nAvailable options:");
        System.out.println("a --> [ Count Words in Each File ]");
        System.out.println("b --> [ Count Number of Lines in Each File ]");
        System.out.print("Enter your option: ");
        // Infinite loop to allow multiple operations until user exits
        while(true){
            char option = sc.next().charAt(0);
            // Exit condition
            if(option=='0'){
                System.out.println(" FileAnalyzer Terminated ");
                break;
            }
            // Perform task based on user choice
                switch (option) {
                case 'a':// Word Count Option
                    FileWordAnalyzer wordAnalyzer = new FileWordAnalyzer(filepath);
                    wordAnalyzer.countWords();
                    break;
                case 'b':// Line Count Option
                    FileLineAnalyzer lineAnalyzer = new FileLineAnalyzer(filepath);
                    lineAnalyzer.countLines();
                    break;
                default:
                    System.out.println("Invalid option!");
            }
            // Ask again for another operation or exit
            System.out.println("Enter option to process available operation or press zero '0' to exit ;");

        }



    }
}

//Word Analyzer
class FileWordAnalyzer {
    private final String filepath; //Path to the file containing list of files
    private final List<FileWordThread> threadList = new ArrayList<>();// List to store all worker threads

    public FileWordAnalyzer(String filepath) {
        this.filepath = filepath;
    }
    // Method to count words in each file
    public void countWords() {
        long startTime=System.currentTimeMillis();
        System.out.println(Utility.formatMillisToTime(System.currentTimeMillis()) + " Starting Word Count");
        try (Scanner sc = new Scanner(new File(filepath))) {
            while (sc.hasNextLine()) {
                String filePathToProcess = sc.nextLine();
                System.out.println(Utility.formatMillisToTime(System.currentTimeMillis()) + " Starting Thread for : [ " + Utility.trimFileName(filePathToProcess) + " ]");
                // Create a thread for each file
                FileWordThread thread = new FileWordThread(filePathToProcess);
                thread.start();// Start the thread
                threadList.add(thread);
            }
            // Wait for all threads to finish
            for (FileWordThread t : threadList) {
                t.join();
            }
            System.out.println(Utility.formatMillisToTime(System.currentTimeMillis()) + " End of Word Count");
            long endTime=System.currentTimeMillis();
            System.out.println("\n" + Utility.formatMillisToTime(System.currentTimeMillis()) + " End of word Count (Total execution time: " + (endTime - startTime) + "ms)");
            // Print summary results of each file
            for (FileWordThread result : threadList) {
                System.out.println("Result of-->"+
                        Utility.trimFileName(result.getFilepath())+
                                " \n-TotalWords-->[ " + result.getTotalWords() + " ]"+  "\n-Execution time-->[ " + result.getExecutionTime() + " ms ]\n"
                );
            }
            // Print detailed word counts for each file
            System.out.println("--------WORDS COUNTS----------");
            for (FileWordThread result : threadList) {
                System.out.println(
                        Utility.trimFileName(result.getFilepath()) +"---->["+ result.getWordCount()+" ]"
                );
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filepath);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
//Thread to Count Words in One File
class FileWordThread extends Thread {
    private final String filepath;
    private int totalWords;
    private long executionTime;
    private final Map<String, Integer> wordCount = new HashMap<>();
    public FileWordThread(String filepath) {
        this.filepath = filepath;
    }public int getTotalWords() {
        return totalWords;
    }public String getFilepath() {
        return filepath;
    }public long getExecutionTime() {
        return executionTime;
    }public Map<String, Integer> getWordCount() {
        return wordCount;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        try (Scanner sc = new Scanner(new File(filepath))) {
            System.out.println(Utility.formatMillisToTime(System.currentTimeMillis()) + "Processing [" + Utility.trimFileName(filepath) + " ] on thread [" + Thread.currentThread().getName() + " ]");

            totalWords = 0;
            // Read file line by line
            while (sc.hasNextLine()) {
                String fileLine = sc.nextLine();
                // Split by non-alphanumeric characters
                String[] words = fileLine.split("[^A-Za-z0-9]+");
                for (String word : words) {
                    if (!word.trim().isEmpty()) {
                        totalWords++;
                        word = word.toLowerCase();
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + filepath);
        }
        System.out.println(Utility.formatMillisToTime(System.currentTimeMillis()) + "Completed Processing  [ " + Utility.trimFileName(filepath) + " ]  on thread [ " + Thread.currentThread().getName() + " ]");

        long endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
    }
}

// Line Analyzer
class FileLineAnalyzer {
    private final String filepath;
    private final List<LineCounterThread> threadList = new ArrayList<>();

    public FileLineAnalyzer(String filepath) {
        this.filepath = filepath;
    }

    // Method to count lines in each file
    public void countLines() {
        long startTime=System.currentTimeMillis();
        System.out.println(Utility.formatMillisToTime(System.currentTimeMillis()) + " Starting Line Count");
        try (Scanner sc = new Scanner(new File(filepath))) {
            while (sc.hasNextLine()) {
                String filePathToProcess = sc.nextLine();
                LineCounterThread thread = new LineCounterThread(filePathToProcess);
                thread.start();
                threadList.add(thread);
            }

            // Wait for all threads to complete
            for (LineCounterThread t : threadList) {
                t.join();
            }
            // Print results for each file
            for (LineCounterThread t : threadList) {
                System.out.println(
                        Utility.trimFileName(t.getFilepath()) +
                                " ---> Contain [ " + t.getLineCount() + " Lines ]" +"and [ Total Execution time :"+ t.getExecutionTime() + " ms]"
                );
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filepath);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime=System.currentTimeMillis();
        System.out.println(Utility.formatMillisToTime(System.currentTimeMillis()) + " End of Line Count");
        System.out.println("Total Execution Time ---> [ "+(endTime-startTime)+"ms ]");
    }
}
//Thread to Count Lines in One File
class LineCounterThread extends Thread {
    private final String filepath;
    private int lineCount = 0;
    private long executionTime;

    public LineCounterThread(String filepath) {
        this.filepath = filepath;
    }public int getLineCount() {
        return lineCount;
    }public String getFilepath() {
        return filepath;
    }public long getExecutionTime() {
        return executionTime;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        try (Scanner sc = new Scanner(new File(filepath))) {
            while (sc.hasNextLine()) {
                String fileline = sc.nextLine();
                String[] lines = fileline.split("\\.");
                for ( String line : lines ) {
                    if (!line.isEmpty()) {
                        lineCount++;
                    }
                }
            }
        }catch (FileNotFoundException e) {
            System.out.println("File not found: " + filepath);
        }
        long endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
    }
}

//  Utility
class Utility {
    public static String formatMillisToTime(long millis) {
        long hours = (millis / (1000 * 60 * 60)) % 24;
        long minutes = (millis / (1000 * 60)) % 60;
        long seconds = (millis / 1000) % 60;
        long ms = millis % 1000;

        return String.format(" [[ %02d:%02d:%02d.%03d ]] ---> ", hours, minutes, seconds, ms);
    }

    public static String trimFileName(String filepath) {
        int index = filepath.lastIndexOf('\\');
        return (index != -1) ? filepath.substring(index + 1) : filepath;
    }
}
