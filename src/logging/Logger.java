package org.example.logging;

import org.example.Config;
//import org.example.Main;

import java.io.FileWriter;
import java.io.IOException;
//import java.io.Writer;

public class Logger {
    public void println(LoggingMode mode, String printOutput){
//    if (Config.loggingMode == LoggingMode.Debug && mode == LoggingMode.Debug) {
//            System.out.println(printOutput);
//        } else if (mode == LoggingMode.Production) {
//            System.out.println(printOutput);

//        }

        if (mode.ordinal() >= Config.loggingMode.ordinal()) {
            if (Config.Outputformat.equalsIgnoreCase("Console")) {
                System.out.println(printOutput);
            } else if (Config.Outputformat.equalsIgnoreCase("File")) {
                try (FileWriter writer = new FileWriter(Config.OutputFilePath, true)) {
                    writer.write(printOutput);

                } catch (IOException e) {
                    System.out.println("Error in File writing");
                }
            }

        }
    }
}
