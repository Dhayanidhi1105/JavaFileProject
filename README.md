# File Analyzer

**File Analyzer** is a Java console application that analyzes text files.  
It can count **words** or **lines** in each file using **multithreading**, and it also shows **how many times each word appears** in each file.  

---

## Features

- Count **total words** in each file  
- Count **total lines** in each file  
- Count **frequency of each word**  
- Multithreaded processing for faster analysis  
- Displays execution time per file and total execution time  

---

## How to Run

1. Upload your text files in the project folder.  
2. Create a text file named `file.txt` in the project folder.  
   This file should contain the list of files to analyze. Example:
file1.txt
file2.txt
file3.txt
3. Open the project in your IDE.  
4. Run the `Main` class.  
5. Follow the console instructions:
a --> Count Words in Each File (shows word frequency)
b --> Count Number of Lines in Each File
0 --> Exit
   - Enter `a` to count words and see **word frequency** per file  
- Enter `b` to count lines  
- Enter `0` to exit  

---

## Output

- Shows **start and end time** for each file  
- Displays **total words or lines** for each file  
- Shows **execution time per file** and **total execution time**  
- Shows **frequency of each word** in the file  
Example snippet:

Result of--> file1.txt

Total Words --> [450]

Execution time --> [75 ms]

--------WORDS COUNTS----------
file1.txt ----> [ {the=50, and=30, java=10, ...} ]
