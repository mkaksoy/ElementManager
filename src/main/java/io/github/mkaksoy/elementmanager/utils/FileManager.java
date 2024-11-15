package io.github.mkaksoy.elementmanager.utils;

import io.github.mkaksoy.elementmanager.utils.levels.Error;
import io.github.mkaksoy.elementmanager.utils.levels.Info;
import io.github.mkaksoy.elementmanager.utils.levels.Warning;

import java.io.File;
import java.io.IOException;

import static io.github.mkaksoy.elementmanager.Main.logger;

public class FileManager {

    public static void createFolder(File folder, String name) {
        if (!folder.exists()) {
            boolean created = folder.mkdirs();

            if (created) {
                logger.log(Info.INFO, "Folder '{0}' was successfully created.", name);
            } else {
                logger.log(Warning.WARNING, "Folder '{0}' already exists, no creation needed.", name);
            }
        } else {
            logger.log(Info.INFO, "Folder '{0}' already exists.", name);
        }
    }

    public static void createFile(File file, String name, Runnable writer) {
        if (!file.exists()) {
            try {
                boolean created = file.createNewFile();

                if (created) {
                    logger.log(Info.INFO, "File '{0}' was successfully created.", name);
                } else {
                    logger.log(Warning.WARNING, "File '{0}' already exists, no creation needed.", name);
                }

                writer.run();
            } catch (IOException e) {
                logger.log(Error.ERROR, "Error while creating or writing to the file: " + name, e);
            }
        } else {
            logger.log(Info.INFO, "File '{0}' already exists.", name);
            writer.run();
        }
    }
}
