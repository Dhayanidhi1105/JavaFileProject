package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileLineAnalyzer {
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

                System.out.println("Paragraph Count--> [ "+t.getParagraphCount()+" ]\n");
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
