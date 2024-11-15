package io.github.mkaksoy.elementmanager.utils;

import io.github.mkaksoy.elementmanager.management.Manager;
import io.github.mkaksoy.elementmanager.utils.levels.Error;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import static io.github.mkaksoy.elementmanager.Main.logger;

public class FileLogger {
    static {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();

            File logsDir = Manager.logs;

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
            logger.log(Error.ERROR, "Error creating logs.", e);
        }
    }

    public static void log(Level level, String message) {
        logger.log(level, message);
    }
}
