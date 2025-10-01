package org.example;

import java.io.File;

import java.io.FileNotFoundException;

import java.util.*;

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

