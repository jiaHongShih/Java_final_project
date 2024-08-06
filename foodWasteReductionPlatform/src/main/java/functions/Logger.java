/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package functions;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author JiaHong
 */

public class Logger {

    private static final String LOG_FILE_PATH = "./log_info.txt";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String message) {
 try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(LOG_FILE_PATH, true), StandardCharsets.UTF_8))) {
            String timestamp = LocalDateTime.now().format(dateTimeFormatter);
            writer.write("[" + timestamp + "] " + message);
            writer.newLine();
            System.out.println("Log written successfully.");
        } catch (IOException e) {
            System.err.println("Failed to write to log file.");
            e.printStackTrace();
        }
    }
}
