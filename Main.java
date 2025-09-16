package org.example;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        File file = new File("C:\\Users\\dhaya\\Filelist\\File.txt");
        Scanner sc = new Scanner(file);

        List<FileBox> list = new ArrayList<>(); // To store FileBox objects
        List<Calculate> obj = new ArrayList<>(); // To store Calculate objects

        while (sc.hasNextLine()) {
            String filepath = sc.nextLine().trim();
            if (!filepath.isEmpty()) {
                Calculate object = new Calculate(filepath);//Create object for each thread
                object.start();//Thread start
                obj.add(object);
            }
        }
        sc.close();

        for (Calculate result : obj) {
            result.join(); // wait until thread finishes
            list.add(result.getObj()); // get result from thread
        }

        long end = System.currentTimeMillis();
        System.out.println("Total execution time:" + (end - start) + "ms\n");

        for (FileBox  result: list) {
            System.out.println("Execution time:" +result.executiontime + "ms");
            System.out.println("Total words:" + result.totalwords + "\n");
        }

        for (FileBox wordcount : list) {
            System.out.println("Word Count Details:");
            System.out.println(wordcount.wordcount);
            System.out.println("-----------------------------------------------\n");
        }
    }
}

class Calculate extends Thread {
    FileBox obj;
    String filepath;

    public Calculate(String filepath) {
        this.filepath = filepath;
    }

    //Thread
    public void run() {
        Map<String, Integer> wordcount = new HashMap<>();
        int totalwords = 0;
        long starttime = System.currentTimeMillis();

        try (Scanner sc = new Scanner(new File(filepath))) {
            while (sc.hasNextLine()) {
                String fileline = sc.nextLine();
                String[] words = fileline.split("[^A-Za-z0-9]+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        totalwords++;
                        wordcount.put(word, wordcount.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("error reading file: " + filepath);
        }

        long endtime = System.currentTimeMillis();
        long executiontime = endtime - starttime;

        obj = new FileBox((int) executiontime, totalwords, wordcount);
    }

    public FileBox getObj() {
        return obj; // return the FileBox object that stores the result
    }
}

class FileBox {
    int executiontime;
    int totalwords;
    Map<String, Integer> wordcount;

    public FileBox(int executiontime, int totalwords, Map<String, Integer> wordcount) {
        this.executiontime = executiontime;
        this.totalwords = totalwords;
        this.wordcount = wordcount;
    }
}
