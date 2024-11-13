package io.github.mkaksoy.elementmanager.utils;

import io.github.mkaksoy.elementmanager.Main;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileLogger {
    private static final Logger logger = Logger.getLogger(FileLogger.class.getName());

    static {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();

            File logsDir = Paths.logs;
            if (!logsDir.exists()) {
                boolean created = logsDir.mkdir();
                if (!created) {
                    Main.logger.warning("Error creating 'management' folder.");
                } else {
                    Main.logger.info("'management' folder created successfully.");
                }
            }

            String logFileName = formatter.format(now) + "_0.log";
            File logFile = new File(logsDir, logFileName);

            int i = 1;
            while (logFile.exists()) {
                logFile = new File(logsDir, formatter.format(now) + "_" + i + ".log");
                i++;
            }


            FileHandler fileHandler = new FileHandler(logFile.getAbsolutePath(), true);
            fileHandler.setFormatter(new Formatter());

            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public static void log(Level level, String message) {
        logger.log(level, message);
    }
}
