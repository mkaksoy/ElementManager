package io.github.mkaksoy.elementmanager.utils;

import io.github.mkaksoy.elementmanager.Main;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static void createFolder(File path, String name) {
        if (!path.exists()) {
            boolean created = path.mkdirs();
            if (!created) {
                Main.logger.severe("Error creating folder: '" + name + "'. Check permissions.");
            }
        }
    }

    public static void createFile(File path, String name, Runnable after) throws IOException {
        if (!path.exists()) {
            boolean created = path.createNewFile();
            if (!created) {
                Main.logger.severe("Error creating file: '" + name + "'. Check permissions.");
            } else {
                Main.logger.info("File '" + name + "' created successfully.");
                after.run();
            }
        } else {
            Main.logger.info("File '" + name + "' already exists.");
        }
    }
}
