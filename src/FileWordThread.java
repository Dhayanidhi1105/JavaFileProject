package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileWordThread extends Thread {
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
