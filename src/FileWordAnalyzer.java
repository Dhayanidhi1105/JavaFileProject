package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class FileWordAnalyzer {
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
