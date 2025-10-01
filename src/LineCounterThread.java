package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
 public class LineCounterThread extends Thread{
    private final String filepath;
    private int lineCount = 0;
    private long executionTime;
    private int paragraphCount;

    public LineCounterThread(String filepath) {
        this.filepath = filepath;
    }public int getLineCount() {
        return lineCount;
    }public String getFilepath() {
        return filepath;
    }public long getExecutionTime() {
        return executionTime;
    }
    public int getParagraphCount() {
         return paragraphCount;
     }

     @Override
    public void run() {
        paragraphCount =1;
        int paraFlag=0;
        long startTime = System.currentTimeMillis();
        try (Scanner sc = new Scanner(new File(filepath))) {
            while (sc.hasNextLine()) {
                String fileline = sc.nextLine();
                if(fileline.isEmpty())
                {
                    if(paraFlag==1)
                    {
                        paragraphCount++;
                        paraFlag=0;
                    }
                }else {
                    paraFlag=1;
                }
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
