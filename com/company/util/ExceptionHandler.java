package com.company.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ExceptionHandler {
    private static final String LOG_FILE = "error.log";

    public static void handle(Exception e){
        if (e == null) return;

        String timestamp = LocalDateTime.now().toString();
        String message = String.format("[%s] Exception: %s - %s",
                timestamp,
                e.getClass().getSimpleName(),
                e.getMessage());

        // 1️⃣ Konsola yazdır
        System.err.println(message);
        e.printStackTrace(System.err);

        // 2️⃣ Dosyaya logla (append mode)
        try (PrintWriter pw = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            pw.println(message);
            e.printStackTrace(pw);
        } catch (IOException ioEx) {
            System.err.println("Failed to write to log file: " + ioEx.getMessage());
        }
    }

}
